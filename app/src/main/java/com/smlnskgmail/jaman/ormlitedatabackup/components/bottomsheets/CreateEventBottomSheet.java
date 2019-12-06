package com.smlnskgmail.jaman.ormlitedatabackup.components.bottomsheets;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.Event;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateEventBottomSheet extends BaseBottomSheet {

    private EventCreateTarget eventCreateTarget;

    public void setupCreateEventTarget(@NonNull EventCreateTarget eventCreateTarget) {
        this.eventCreateTarget = eventCreateTarget;
    }

    @Override
    public void init(View view) {
        Date now = Calendar.getInstance().getTime();

        applyTextTo(
                view.findViewById(R.id.new_event_date),
                formatDate(now, DateFormat.MEDIUM)
        );

        applyTextTo(
                view.findViewById(R.id.new_event_time),
                formatDate(now, DateFormat.SHORT)
        );

        view.findViewById(R.id.add_new_event_close).setOnClickListener(button -> dismiss());
        view.findViewById(R.id.add_new_event).setOnClickListener(button -> {
            eventCreateTarget.eventAdded(
                    new Event(
                            textFrom(view.findViewById(R.id.new_event_title)),
                            textFrom(view.findViewById(R.id.new_event_subtitle)),
                            now
                    )
            );
            dismiss();
        });
    }

    private void applyTextTo(@NonNull TextView textView, @NonNull String text) {
        textView.setText(text);
    }

    @NonNull
    private String textFrom(@NonNull EditText editText) {
        return editText.getText().toString();
    }

    @NonNull
    private String formatDate(@NonNull Date date, int type) {
        return DateFormat.getDateInstance(type).format(date);
    }

    @Override
    public int layoutResId() {
        return R.layout.bottom_sheet_create_event;
    }

    public interface EventCreateTarget {

        void eventAdded(@NonNull Event event);

    }

}
