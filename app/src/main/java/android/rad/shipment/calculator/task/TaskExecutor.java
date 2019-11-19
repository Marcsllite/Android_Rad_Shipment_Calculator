package android.rad.shipment.calculator.task;


import androidx.annotation.NonNull;

/**
 * Created by renarosantos on 20/02/16.
 */
public interface TaskExecutor {

    <T> void async(@NonNull final AppTask<T> task);
}
