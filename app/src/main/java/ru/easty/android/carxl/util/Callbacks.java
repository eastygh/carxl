package ru.easty.android.carxl.util;

public class Callbacks {
    public Runnable onError = () -> {
    };
    public Runnable onFinish = () -> {
    };

    public Callbacks() {
    }

    public Callbacks(Runnable onError, Runnable onFinish) {
        if (onError != null) this.onError = onError;
        if (onFinish != null) this.onFinish = onFinish;
    }
}
