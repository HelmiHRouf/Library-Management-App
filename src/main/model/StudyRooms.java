package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represent a list of study room. As a user, they can book and cancel book of the study room. As a librarian, they can
// get all the study room information and get all the study room that is booked and who booked it.
public class StudyRooms implements Writable {
    private List<StudyRoom> listStudyRoom;


    // EFFECTS: construct a StudyRooms
    public StudyRooms() {
        listStudyRoom = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a study room in a study rooms;
    public void addStudyRoom(StudyRoom studyRoom) {
        this.listStudyRoom.add(studyRoom);
        EventLog.getInstance().logEvent(new Event("Added Study Room " + studyRoom.getRoomID()));
    }

    // EFFECTS: decide whether the room can be booked
    public boolean canTheRoomBooked(int roomId) {
        StudyRoom room = listStudyRoom.get(roomId);
        return (room.getBooked().equals(""));
    }

    // MODIFIES: this, User, StudyRoom
    // EFFECTS: book a study room, set the booked information, booker information, and tell who booked the room
    public void bookStudyRoom(User user, int roomId) {
        StudyRoom studyRoom = listStudyRoom.get(roomId);
        studyRoom.setBooker(user.getUsername());
        user.setRoomBooked(roomId);
        EventLog.getInstance().logEvent(new Event("Study room " + roomId + " booked by "
                + user.getUsername()));
    }

    // MODIFIES: this, User
    // EFFECTS: cancel book a study room, remove the booked information, booker information, and who booked the room
    public void cancelBookStudyRoom(User user) {
        int studyRoom = user.getRoomBooked();
        user.setRoomBooked(-1);
        StudyRoom room = listStudyRoom.get(studyRoom);
        room.setBooker("");
        EventLog.getInstance().logEvent(new Event("Study room " + studyRoom + " cancelled by "
                + user.getUsername()));
    }

    // EFFECTS: return all the study room that is booked
    public List<StudyRoom> listBookedRooms() {
        List<StudyRoom> listBookedRooms = new ArrayList<>();
        for (StudyRoom room : listStudyRoom) {
            if (!room.getBooked().equals("")) {
                listBookedRooms.add(room);
            }
        }
        EventLog.getInstance().logEvent(new Event("Display all study rooms that is booked"));
        return listBookedRooms;
    }

    // EFFECTS: pass all the StudyRoom
    public List<StudyRoom> getListStudyRoom() {
        EventLog.getInstance().logEvent(new Event("Display study rooms"));
        return listStudyRoom;
    }

    // EFFECTS: turn StudyRooms object to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonStudyRooms = new JSONObject();
        jsonStudyRooms.put("study rooms", studyRoomToJson());
        return jsonStudyRooms;
    }

    // EFFECTS: turn StudyRoom object to JSON object, put it to the StudyRooms JSON object.
    private JSONArray studyRoomToJson() {
        JSONArray jsonArray = new JSONArray();
        for (StudyRoom studyRoom : listStudyRoom) {
            jsonArray.put(studyRoom.toJson());
        }

        return jsonArray;
    }
}
