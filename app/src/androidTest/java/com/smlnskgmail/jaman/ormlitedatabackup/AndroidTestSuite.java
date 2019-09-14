package com.smlnskgmail.jaman.ormlitedatabackup;

import com.smlnskgmail.jaman.ormlitedatabackup.features.backup.CreateLocalBackupTest;
import com.smlnskgmail.jaman.ormlitedatabackup.features.db.data.DefaultDataTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DefaultDataTest.class,
        CreateLocalBackupTest.class
})
public class AndroidTestSuite {}
