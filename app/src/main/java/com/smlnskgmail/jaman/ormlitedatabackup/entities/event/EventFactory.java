package com.smlnskgmail.jaman.ormlitedatabackup.entities.event;

import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;

import java.sql.SQLException;
import java.util.List;

public class EventFactory {

    public static List<Event> allEvents() throws SQLException {
        return HelperFactory.instance().allOf(Event.class);
    }

    public static void save(Event event) throws SQLException {
        HelperFactory.instance().saveAll(Event.class, event);
    }

    public static void saveAll(Event ...events) throws SQLException {
        HelperFactory.instance().saveAll(Event.class, events);
    }

}
