package io.memetic.alcohdex;

import android.app.Application;

import io.memetic.alcohdex.core.components.ComponentRegistry;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
public class App extends Application {
    public ComponentRegistry getComponentRegistry() {
        return ComponentRegistry.getInstance();
    }
}
