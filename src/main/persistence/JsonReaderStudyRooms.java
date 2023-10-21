package persistence;

import model.StudyRoom;
import model.StudyRooms;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderStudyRooms {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderStudyRooms(String source) {
        this.source = source;
    }

    // EFFECTS: reads StudyRooms from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StudyRooms read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudyRooms(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses StudyRooms from JSON object and returns it
    private StudyRooms parseStudyRooms(JSONObject jsonObject) {
        StudyRooms studyRooms = new StudyRooms();
        addStudyRooms(studyRooms, jsonObject);
        return studyRooms;
    }

    // MODIFIES: studyRooms
    // EFFECTS: parses listStudyRoom from JSON object and adds them to StudyRooms
    private void addStudyRooms(StudyRooms studyRooms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("study rooms");
        for (Object json : jsonArray) {
            JSONObject nextStudyRoom = (JSONObject) json;
            addStudyRoom(studyRooms, nextStudyRoom);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses StudyRoom from JSON object and adds it to StudyRooms
    private void addStudyRoom(StudyRooms studyRooms, JSONObject jsonObject) {
        StudyRoom studyRoom = getStudyRoom(jsonObject);
        studyRooms.addStudyRoom(studyRoom);
    }

    // MODIFIES: this
    // EFFECTS: a helper to parse StudyRoom from JSON object
    public StudyRoom getStudyRoom(JSONObject jsonObject) {
        User booker;
        String roomID = jsonObject.getString("room ID");
        boolean isBooked = jsonObject.getBoolean("isBooked");
        if (jsonObject.isNull("booker")) {
            booker = null;
        } else {
            booker  = readUser(jsonObject.getJSONObject("booker"));
        }
        StudyRoom studyRoom = new StudyRoom(roomID);
        studyRoom.setBooked(isBooked);
        studyRoom.setBooker(booker);
        return studyRoom;
    }


}