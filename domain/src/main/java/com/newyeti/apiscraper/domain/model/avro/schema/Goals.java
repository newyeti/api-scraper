/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.newyeti.apiscraper.domain.model.avro.schema;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Goals extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1220803172260974102L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Goals\",\"namespace\":\"com.newyeti.apiscraper.domain.model.avro.schema\",\"fields\":[{\"name\":\"goalsAgainst\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"goalsFor\",\"type\":[\"null\",\"int\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Goals> ENCODER =
      new BinaryMessageEncoder<Goals>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Goals> DECODER =
      new BinaryMessageDecoder<Goals>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Goals> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Goals> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Goals>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Goals to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Goals from a ByteBuffer. */
  public static Goals fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Integer goalsAgainst;
  @Deprecated public java.lang.Integer goalsFor;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Goals() {}

  /**
   * All-args constructor.
   * @param goalsAgainst The new value for goalsAgainst
   * @param goalsFor The new value for goalsFor
   */
  public Goals(java.lang.Integer goalsAgainst, java.lang.Integer goalsFor) {
    this.goalsAgainst = goalsAgainst;
    this.goalsFor = goalsFor;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return goalsAgainst;
    case 1: return goalsFor;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: goalsAgainst = (java.lang.Integer)value$; break;
    case 1: goalsFor = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'goalsAgainst' field.
   * @return The value of the 'goalsAgainst' field.
   */
  public java.lang.Integer getGoalsAgainst() {
    return goalsAgainst;
  }

  /**
   * Sets the value of the 'goalsAgainst' field.
   * @param value the value to set.
   */
  public void setGoalsAgainst(java.lang.Integer value) {
    this.goalsAgainst = value;
  }

  /**
   * Gets the value of the 'goalsFor' field.
   * @return The value of the 'goalsFor' field.
   */
  public java.lang.Integer getGoalsFor() {
    return goalsFor;
  }

  /**
   * Sets the value of the 'goalsFor' field.
   * @param value the value to set.
   */
  public void setGoalsFor(java.lang.Integer value) {
    this.goalsFor = value;
  }

  /**
   * Creates a new Goals RecordBuilder.
   * @return A new Goals RecordBuilder
   */
  public static com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder newBuilder() {
    return new com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder();
  }

  /**
   * Creates a new Goals RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Goals RecordBuilder
   */
  public static com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder newBuilder(com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder other) {
    return new com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder(other);
  }

  /**
   * Creates a new Goals RecordBuilder by copying an existing Goals instance.
   * @param other The existing instance to copy.
   * @return A new Goals RecordBuilder
   */
  public static com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder newBuilder(com.newyeti.apiscraper.domain.model.avro.schema.Goals other) {
    return new com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder(other);
  }

  /**
   * RecordBuilder for Goals instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Goals>
    implements org.apache.avro.data.RecordBuilder<Goals> {

    private java.lang.Integer goalsAgainst;
    private java.lang.Integer goalsFor;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.goalsAgainst)) {
        this.goalsAgainst = data().deepCopy(fields()[0].schema(), other.goalsAgainst);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.goalsFor)) {
        this.goalsFor = data().deepCopy(fields()[1].schema(), other.goalsFor);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Goals instance
     * @param other The existing instance to copy.
     */
    private Builder(com.newyeti.apiscraper.domain.model.avro.schema.Goals other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.goalsAgainst)) {
        this.goalsAgainst = data().deepCopy(fields()[0].schema(), other.goalsAgainst);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.goalsFor)) {
        this.goalsFor = data().deepCopy(fields()[1].schema(), other.goalsFor);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'goalsAgainst' field.
      * @return The value.
      */
    public java.lang.Integer getGoalsAgainst() {
      return goalsAgainst;
    }

    /**
      * Sets the value of the 'goalsAgainst' field.
      * @param value The value of 'goalsAgainst'.
      * @return This builder.
      */
    public com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder setGoalsAgainst(java.lang.Integer value) {
      validate(fields()[0], value);
      this.goalsAgainst = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'goalsAgainst' field has been set.
      * @return True if the 'goalsAgainst' field has been set, false otherwise.
      */
    public boolean hasGoalsAgainst() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'goalsAgainst' field.
      * @return This builder.
      */
    public com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder clearGoalsAgainst() {
      goalsAgainst = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'goalsFor' field.
      * @return The value.
      */
    public java.lang.Integer getGoalsFor() {
      return goalsFor;
    }

    /**
      * Sets the value of the 'goalsFor' field.
      * @param value The value of 'goalsFor'.
      * @return This builder.
      */
    public com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder setGoalsFor(java.lang.Integer value) {
      validate(fields()[1], value);
      this.goalsFor = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'goalsFor' field has been set.
      * @return True if the 'goalsFor' field has been set, false otherwise.
      */
    public boolean hasGoalsFor() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'goalsFor' field.
      * @return This builder.
      */
    public com.newyeti.apiscraper.domain.model.avro.schema.Goals.Builder clearGoalsFor() {
      goalsFor = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Goals build() {
      try {
        Goals record = new Goals();
        record.goalsAgainst = fieldSetFlags()[0] ? this.goalsAgainst : (java.lang.Integer) defaultValue(fields()[0]);
        record.goalsFor = fieldSetFlags()[1] ? this.goalsFor : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Goals>
    WRITER$ = (org.apache.avro.io.DatumWriter<Goals>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Goals>
    READER$ = (org.apache.avro.io.DatumReader<Goals>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
