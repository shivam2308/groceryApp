// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: summaryPb.proto

package com.amazaar.Protobuff;

public final class SummaryPbOuterClass {
  private SummaryPbOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SummaryPbOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.amazaar.Protobuff.SummaryPb)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 resultCount = 1;</code>
     * @return The resultCount.
     */
    int getResultCount();
  }
  /**
   * Protobuf type {@code com.amazaar.Protobuff.SummaryPb}
   */
  public static final class SummaryPb extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.amazaar.Protobuff.SummaryPb)
      SummaryPbOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use SummaryPb.newBuilder() to construct.
    private SummaryPb(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SummaryPb() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new SummaryPb();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private SummaryPb(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              resultCount_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.amazaar.Protobuff.SummaryPbOuterClass.internal_static_com_amazaar_Protobuff_SummaryPb_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.amazaar.Protobuff.SummaryPbOuterClass.internal_static_com_amazaar_Protobuff_SummaryPb_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb.class, com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb.Builder.class);
    }

    public static final int RESULTCOUNT_FIELD_NUMBER = 1;
    private int resultCount_;
    /**
     * <code>int32 resultCount = 1;</code>
     * @return The resultCount.
     */
    @java.lang.Override
    public int getResultCount() {
      return resultCount_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (resultCount_ != 0) {
        output.writeInt32(1, resultCount_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (resultCount_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, resultCount_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb)) {
        return super.equals(obj);
      }
      com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb other = (com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb) obj;

      if (getResultCount()
          != other.getResultCount()) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + RESULTCOUNT_FIELD_NUMBER;
      hash = (53 * hash) + getResultCount();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.amazaar.Protobuff.SummaryPb}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.amazaar.Protobuff.SummaryPb)
        com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPbOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.amazaar.Protobuff.SummaryPbOuterClass.internal_static_com_amazaar_Protobuff_SummaryPb_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.amazaar.Protobuff.SummaryPbOuterClass.internal_static_com_amazaar_Protobuff_SummaryPb_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb.class, com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb.Builder.class);
      }

      // Construct using com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        resultCount_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.amazaar.Protobuff.SummaryPbOuterClass.internal_static_com_amazaar_Protobuff_SummaryPb_descriptor;
      }

      @java.lang.Override
      public com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb getDefaultInstanceForType() {
        return com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb.getDefaultInstance();
      }

      @java.lang.Override
      public com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb build() {
        com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb buildPartial() {
        com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb result = new com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb(this);
        result.resultCount_ = resultCount_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb) {
          return mergeFrom((com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb other) {
        if (other == com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb.getDefaultInstance()) return this;
        if (other.getResultCount() != 0) {
          setResultCount(other.getResultCount());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int resultCount_ ;
      /**
       * <code>int32 resultCount = 1;</code>
       * @return The resultCount.
       */
      @java.lang.Override
      public int getResultCount() {
        return resultCount_;
      }
      /**
       * <code>int32 resultCount = 1;</code>
       * @param value The resultCount to set.
       * @return This builder for chaining.
       */
      public Builder setResultCount(int value) {
        
        resultCount_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 resultCount = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearResultCount() {
        
        resultCount_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.amazaar.Protobuff.SummaryPb)
    }

    // @@protoc_insertion_point(class_scope:com.amazaar.Protobuff.SummaryPb)
    private static final com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb();
    }

    public static com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SummaryPb>
        PARSER = new com.google.protobuf.AbstractParser<SummaryPb>() {
      @java.lang.Override
      public SummaryPb parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SummaryPb(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SummaryPb> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SummaryPb> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.amazaar.Protobuff.SummaryPbOuterClass.SummaryPb getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_amazaar_Protobuff_SummaryPb_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_amazaar_Protobuff_SummaryPb_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017summaryPb.proto\022\025com.amazaar.Protobuff" +
      "\" \n\tSummaryPb\022\023\n\013resultCount\030\001 \001(\005b\006prot" +
      "o3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_amazaar_Protobuff_SummaryPb_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_amazaar_Protobuff_SummaryPb_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_amazaar_Protobuff_SummaryPb_descriptor,
        new java.lang.String[] { "ResultCount", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
