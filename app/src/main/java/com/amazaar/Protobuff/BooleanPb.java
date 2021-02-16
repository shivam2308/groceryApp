// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booleanPb.proto

package com.amazaar.Protobuff;

public final class BooleanPb {
  private BooleanPb() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code com.amazaar.Protobuff.BooleanEnum}
   */
  public enum BooleanEnum
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>DEFAULT = 0;</code>
     */
    DEFAULT(0),
    /**
     * <code>TRUE = 1;</code>
     */
    TRUE(1),
    /**
     * <code>FALSE = 2;</code>
     */
    FALSE(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>DEFAULT = 0;</code>
     */
    public static final int DEFAULT_VALUE = 0;
    /**
     * <code>TRUE = 1;</code>
     */
    public static final int TRUE_VALUE = 1;
    /**
     * <code>FALSE = 2;</code>
     */
    public static final int FALSE_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static BooleanEnum valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static BooleanEnum forNumber(int value) {
      switch (value) {
        case 0: return DEFAULT;
        case 1: return TRUE;
        case 2: return FALSE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<BooleanEnum>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        BooleanEnum> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<BooleanEnum>() {
            public BooleanEnum findValueByNumber(int number) {
              return BooleanEnum.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.amazaar.Protobuff.BooleanPb.getDescriptor().getEnumTypes().get(0);
    }

    private static final BooleanEnum[] VALUES = values();

    public static BooleanEnum valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private BooleanEnum(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.amazaar.Protobuff.BooleanEnum)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017booleanPb.proto\022\025com.amazaar.Protobuff" +
      "*/\n\013BooleanEnum\022\013\n\007DEFAULT\020\000\022\010\n\004TRUE\020\001\022\t" +
      "\n\005FALSE\020\002b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
  }

  // @@protoc_insertion_point(outer_class_scope)
}