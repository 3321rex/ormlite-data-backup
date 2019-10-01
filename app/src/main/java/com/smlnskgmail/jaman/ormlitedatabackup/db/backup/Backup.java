package com.smlnskgmail.jaman.ormlitedatabackup.db.backup;

import android.content.Context;

import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.LocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.create.CreateLocalBackup;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.create.CreateLocalBackupTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.restore.RestoreLocalBackup;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.restore.RestoreLocalBackupTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;

public class Backup {

    private final Context context;
    private CreateLocalBackupTarget createLocalBackupTarget;
    private RestoreLocalBackupTarget restoreLocalBackupTarget;

    public Backup(Context context, CreateLocalBackupTarget createLocalBackupTarget) {
        this.context = context;
        this.createLocalBackupTarget = createLocalBackupTarget;
    }

    public Backup(Context context, RestoreLocalBackupTarget restoreLocalBackupTarget) {
        this.context = context;
        this.restoreLocalBackupTarget = restoreLocalBackupTarget;
    }

    public void createLocalBackup() {
        new CreateLocalBackup(context, createLocalBackupTarget, new ErrorLog()).execute();
    }

    public void restoreLocalBackup() {
        DatabaseParameters databaseParameters = HelperFactory.instance().databaseParameters();
        String backupPath = new LocalBackupPath(databaseParameters).pathAsString();

        new RestoreLocalBackup(context, restoreLocalBackupTarget, backupPath, new ErrorLog()).execute();
    }

}