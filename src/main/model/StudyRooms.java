package model;

import java.util.ArrayList;
import java.util.List;

public class StudyRooms {
    private List<StudyRoom> listStudyRoom;


    // EFFECTS: construct a StudyRooms
    public StudyRooms() {
        listStudyRoom = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a study room in a study rooms;
    public void addStudyRoom(StudyRoom studyRoom) {
        this.listStudyRoom.add(studyRoom);
    }

    // EFFECTS: decide whether the room can be booked
    public boolean canTheRoomBooked(int roomId) {
        StudyRoom room = listStudyRoom.get(roomId);
        return !(room.getIsBooked());
    }

    // MODIFIES: this
    // EFFECTS: book a study room, set the booked information, booker information, and tell who booked the room
    public void bookStudyRoom(User user, int roomId) {
        StudyRoom studyRoom = listStudyRoom.get(roomId);
        studyRoom.setBooked(true);
        studyRoom.setBooker(user);
        user.setRoomBooked(studyRoom);
    }

    // MODIFIES: this
    // EFFECTS: cancel book a study room, remove the booked information, booker information, and who booked the room
    public void cancelBookStudyRoom(User user) {
        StudyRoom studyRoom = user.getRoomBooked();
        studyRoom.setBooked(false);
        studyRoom.setBooker(null);
        user.setRoomBooked(null);
    }

    // EFFECTS: return all the study room that is booked
    public List<StudyRoom> listBookedRooms() {
        List<StudyRoom> listBookedRooms = new ArrayList<>();
        for (StudyRoom room : listStudyRoom) {
            if (room.getIsBooked()) {
                listBookedRooms.add(room);
            }
        }
        return listBookedRooms;
    }

    // EFFECTS: pass all the StudyRoom
    public List<StudyRoom> getListStudyRoom() {
        return listStudyRoom;
    }
}