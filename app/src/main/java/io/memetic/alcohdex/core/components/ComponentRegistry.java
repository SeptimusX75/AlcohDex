package io.memetic.alcohdex.core.components;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
public class ComponentRegistry {
    private static final ComponentRegistry mInstance = new ComponentRegistry();
    public final RepositoryComponent repoComponent = DaggerRepositoryComponent.create();

    private ComponentRegistry() {
    }

    public static ComponentRegistry getInstance() {
        return mInstance;
    }
}
