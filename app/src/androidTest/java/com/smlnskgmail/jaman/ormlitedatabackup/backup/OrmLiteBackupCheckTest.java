package com.smlnskgmail.jaman.ormlitedatabackup.backup;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.OrmLiteBackupCheck;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class OrmLiteBackupCheckTest {

    private final Context context = InstrumentationRegistry.getInstrumentation().getContext();
    private final Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private OrmLiteDatabaseParameters ORMLiteDatabaseParameters;

    @Before
    public void init() {
        ORMLiteDatabaseParameters = OrmLiteHelperFactory.databaseParameters();
    }

    @Test
    public void checkValidDb() throws IOException {
        copyBackupFileToAppFolder(true);
        tryRestoreBackup(true);
    }

    @Test
    public void checkInvalidDb() throws IOException {
        copyBackupFileToAppFolder(false);
        tryRestoreBackup(false);
    }

    private void copyBackupFileToAppFolder(boolean isValid) throws IOException {
        String backupFolder = isValid ? "valid" : "invalid";
        InputStream backupFile = context.getResources().getAssets().open(
                "backups/"
                        + backupFolder
                        + "/ormlite.backup"
        );

        new FileCopy(
                targetContext,
                backupFile,
                ORMLiteDatabaseParameters.databasePath()
        ).copy();
    }

    private void tryRestoreBackup(boolean isValid) {
        OrmLiteBackupCheck backupCheck = new OrmLiteBackupCheck(
                targetContext,
                ORMLiteDatabaseParameters.databasePath()
        );

        assertEquals(
                isValid,
                backupCheck.isValidDB()
        );
    }

}
