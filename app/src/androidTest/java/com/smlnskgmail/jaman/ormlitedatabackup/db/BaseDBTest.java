package com.smlnskgmail.jaman.ormlitedatabackup.db;

import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;

import org.junit.Before;

import java.sql.SQLException;

public abstract class BaseDBTest {

    @Before
    public void cleanAll() throws SQLException {
        HelperFactory.instance().cleanAll(Event.class);
    }

}
