package io.memetic.alcohdex;

import io.memetic.alcohdex.core.dagger.components.AppComponent;
import io.memetic.alcohdex.core.dagger.components.DaggerAppComponent;
import io.memetic.alcohdex.core.dagger.modules.AppModule;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
public class ComponentRegistry {
    private static final ComponentRegistry mInstance = new ComponentRegistry();
    private AppComponent mAppComponent;

    private ComponentRegistry() {
    }

    public static ComponentRegistry getInstance() {
        return mInstance;
    }

    void initialize(App app) {
        AppModule appModule = new AppModule(app);
        mAppComponent = DaggerAppComponent.builder().appModule(appModule).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
