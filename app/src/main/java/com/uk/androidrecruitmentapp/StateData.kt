package com.uk.androidrecruitmentapp

import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import org.jetbrains.annotations.TestOnly

public class StateData<T> {
    @NonNull
    private var status: DataStatus? = null;

    @Nullable
    private var data: T? = null;

    @Nullable
    private var error: Throwable? = null;

    public fun StateData() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
    }

    public fun loading(): StateData<T> {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public fun success(@NonNull data: T): StateData<T> {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public fun error(@NonNull error: Throwable): StateData<T> {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    public fun complete(): StateData<T> {
        this.status = DataStatus.COMPLETE;
        return this;
    }

    @NonNull
    public fun getStatus(): DataStatus {
        return status!!
    }

    @TestOnly
    public fun setStatus(dataStatus : DataStatus) {
        status = dataStatus
    }

    @Nullable
    public fun getData(): T {
        return data!!
    }

    @TestOnly
    public fun setData(data : T) {
        this.data = data
    }

    @Nullable
    public fun getError(): Throwable {
        return error!!
    }

    public enum class DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING,
        COMPLETE
    }
}