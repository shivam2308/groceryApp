package com.amazaar.CommonCode;

import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Generic ProtoJsonUtil to be used to serialize and deserialize Proto to json
 *
 * @author Marcello_deSales@intuit.com
 *
 */
public final class ProtoJsonUtil {

    /**
     * Makes a Json from a given message or builder
     *
     * @param messageOrBuilder is the instance
     * @return The string representation
     * @throws IOException if any error occurs
     */
    public static String toJson(MessageOrBuilder messageOrBuilder) throws IOException {
        return JsonFormat.printer().print(messageOrBuilder);
    }

    /**
     * Makes a new instance of message based on the json and the class
     * @param <T> is the class type
     * @param json is the json instance
     * @param clazz is the class instance
     * @return An instance of T based on the json values
     * @throws IOException if any error occurs
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends Message> T fromJson(String json, Class<T> clazz) throws IOException {
        // https://stackoverflow.com/questions/27642021/calling-parsefrom-method-for-generic-protobuffer-class-in-java/33701202#33701202
        Builder builder = null;
        try {
            // Since we are dealing with a Message type, we can call newBuilder()
            builder = (Builder) clazz.getMethod("newBuilder").invoke(null);

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            return null;
        }

        // The instance is placed into the builder values
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);

        // the instance will be from the build
        return (T) builder.build();
    }
}
