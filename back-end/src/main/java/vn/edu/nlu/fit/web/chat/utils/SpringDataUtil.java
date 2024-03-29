package vn.edu.nlu.fit.web.chat.utils;

import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for copying and manipulating object properties.
 *
 * @author Menes
 */

public class SpringDataUtil {
    private SpringDataUtil(){}


    private static final Logger LOGGER = Logger.getLogger(SpringDataUtil.class.getName());

    /**
     * Copies properties from the source object to a new instance of the target class,
     * excluding properties specified in the `ignoreProperties` array.
     *
     * @param source           The source object to copy properties from. (must not be null)
     * @param classTarget      The target class to create an instance of. (must not be null)
     * @param ignoreProperties An array of property names to exclude from copying. (optional)
     * @param <T>              The type of the target object.
     * @return A new instance of the target class with copied properties.
     * @throws RuntimeException If an error occurs during property copying.
     */

    public static <T> T copyProperties(Object source, Class<T> classTarget, String... ignoreProperties) {
        Assert.notNull(source, "Source object cannot be null");
        Assert.notNull(classTarget, "Target class cannot be null");

        try {
            T targetInstance = classTarget.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
            return targetInstance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, "Error copying properties", e);
            throw new RuntimeException("Error copying properties", e);
        }
    }

    /**
     * Convenience method that calls {@link #copyProperties(Object, Class, String[])} with a null `ignoreProperties` array.
     * Copies all properties from the source object to a new instance of the target class.
     *
     * @param source      The source object to copy properties from. (must not be null)
     * @param classTarget The target class to create an instance of. (must not be null)
     * @param <T>         The type of the target object.
     * @return A new instance of the target class with copied properties.
     * @throws RuntimeException If an error occurs during property copying.
     */
    public static <T> T copyProperties(Object source, Class<T> classTarget) {
        return copyProperties(source, classTarget, (String[]) null);
    }


    /**
     * Attempts to create a deep copy of the source object by performing a full property copy.
     * **Note:** This method uses reflection and may not be performant or handle complex object structures effectively.
     * Consider dedicated deep copy libraries for advanced scenarios.
     *
     * @param source      The source object to deep copy. (must not be null)
     * @param classTarget The target class for the deep copy. (must not be null)
     * @param <T>         The type of the target object.
     * @return A new instance of the target class with a deep copy of the source object's properties.
     * @throws RuntimeException If an error occurs during property copying.
     */
    public static <T> T deepCopyProperties(Object source, Class<T> classTarget) {
        Assert.notNull(source, "Source object cannot be null");
        Assert.notNull(classTarget, "Target class cannot be null");

        try {
            T targetInstance = classTarget.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetInstance);
            return targetInstance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, "Error copying properties", e);
            throw new RuntimeException("Error copying properties", e);
        }
    }

    /**
     * Copies properties from the source object to a new instance of the target class,
     * handling null values based on the `includeNull` flag.
     * <p>
     * - If `includeNull` is true, all properties are copied, regardless of null values in the source.
     * - If `includeNull` is false (default), null values in the source are not copied to the target.
     *
     * @param source      The source object to copy properties from. (must not be null)
     * @param classTarget The target class to create an instance of. (must not be null)
     * @param includeNull A flag indicating whether to include null values from the source.
     * @param <T>         The type of the target object.
     * @return A new instance of the target class with copied properties (handling null values).
     * @throws RuntimeException If an error occurs during property copying.
     */
    public static <T> T copyPropertiesWithNullHandling(Object source, Class<T> classTarget, boolean includeNull) {
        Assert.notNull(source, "Source object cannot be null");
        Assert.notNull(classTarget, "Target class cannot be null");

        try {
            T targetInstance = classTarget.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetInstance);
            if (!includeNull) {
                Field[] fields = classTarget.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.get(targetInstance) == null) {
                        field.set(targetInstance, field.get(source));
                    }
                }
            }
            return targetInstance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, "Error copying properties", e);
            throw new RuntimeException("Error copying properties", e);
        }
    }

    /**
     * Merges properties from the source object into an existing target object.
     * Existing properties in the target object are overwritten with corresponding values from the source.
     *
     * @param source The source object to copy properties from. (must not be null)
     * @param target The target object to merge properties into. (must not be null)
     * @throws RuntimeException If an error occurs during property copying.
     */
    public static <T> void mergeProperties(Object source, T target) {
        Assert.notNull(source, "Source object cannot be null");
        Assert.notNull(target, "Target object cannot be null");

        BeanUtils.copyProperties(source, target, Arrays.toString(BeanUtils.getPropertyDescriptors(target.getClass())));
    }


}
