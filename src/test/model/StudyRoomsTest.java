package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudyRoomsTest {
    private StudyRooms testStudyRooms;
    private StudyRoom studyRoom1;
    private StudyRoom studyRoom2;
    private StudyRoom studyRoom3;
    private StudyRoom studyRoom4;
    private User user1;
    private User user2;

    @BeforeEach
    void runBefore() {
        testStudyRooms = new StudyRooms();
        studyRoom1 = new StudyRoom(0);
        studyRoom2 = new StudyRoom(01);
        studyRoom3 = new StudyRoom(02);
        studyRoom4 = new StudyRoom(03);
        user1 = new User("aaa", "bbb");
        user2 = new User("ccc", "ddd");
    }
    // make 7 test

    @Test
    void testAddStudyRoom() {
        assertEquals(0, testStudyRooms.getListStudyRoom().size());
        testStudyRooms.addStudyRoom(studyRoom1);
        testStudyRooms.addStudyRoom(studyRoom2);
        testStudyRooms.addStudyRoom(studyRoom3);
        assertEquals(studyRoom1, testStudyRooms.getListStudyRoom().get(0));
        assertEquals(studyRoom2, testStudyRooms.getListStudyRoom().get(1));
        assertEquals(studyRoom3, testStudyRooms.getListStudyRoom().get(2));
        assertEquals(3, testStudyRooms.getListStudyRoom().size());
    }

    @Test
    void testCanTheRoomBooked() {
        testStudyRooms.addStudyRoom(studyRoom1);
        testStudyRooms.addStudyRoom(studyRoom2);
        testStudyRooms.addStudyRoom(studyRoom3);
        assertTrue(testStudyRooms.canTheRoomBooked(0));
        assertTrue(testStudyRooms.canTheRoomBooked(1));
        assertTrue(testStudyRooms.canTheRoomBooked(2));
        studyRoom1.setBooked(true);
        assertFalse(testStudyRooms.canTheRoomBooked(0));
    }

    @Test
    void testBookAndUnbookStudyRoom() {
        testStudyRooms.addStudyRoom(studyRoom1);
        testStudyRooms.addStudyRoom(studyRoom2);
        testStudyRooms.addStudyRoom(studyRoom3);

        testStudyRooms.bookStudyRoom(user1, 0);
        assertEquals(user1, studyRoom1.getBooked());
        assertTrue(studyRoom1.getIsBooked());

        testStudyRooms.bookStudyRoom(user2, 1);
        assertEquals(user2, studyRoom2.getBooked());
        assertTrue(studyRoom1.getIsBooked());

        testStudyRooms.cancelBookStudyRoom(user1);
        assertEquals(null, studyRoom1.getBooked());
        assertFalse(studyRoom1.getIsBooked());

        testStudyRooms.cancelBookStudyRoom(user2);
        assertEquals(null, studyRoom2.getBooked());
        assertFalse(studyRoom2.getIsBooked());
    }

    @Test
    void testTheGetter() {
        testStudyRooms.addStudyRoom(studyRoom1);
        testStudyRooms.addStudyRoom(studyRoom2);
        testStudyRooms.addStudyRoom(studyRoom3);

        List<StudyRoom> testList = new ArrayList<>();
        assertEquals(testList, testStudyRooms.listBookedRooms());
        testList.add(studyRoom1);
        testList.add(studyRoom2);
        testList.add(studyRoom3);

        assertEquals(testList, testStudyRooms.getListStudyRoom());

        studyRoom1.setBooked(true);
        studyRoom3.setBooked(true);

        List<StudyRoom> testList2 = new ArrayList<>();
        testList2.add(studyRoom1);
        testList2.add(studyRoom3);

        assertEquals(testList2, testStudyRooms.listBookedRooms());
        assertEquals(testList, testStudyRooms.getListStudyRoom());
    }

}
