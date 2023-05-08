/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.newyeti.common.avro.schema;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Team extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2196402815398163802L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Team\",\"namespace\":\"com.newyeti.common.avro.schema\",\"fields\":[{\"name\":\"id\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"logo\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Team> ENCODER =
      new BinaryMessageEncoder<Team>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Team> DECODER =
      new BinaryMessageDecoder<Team>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Team> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Team> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Team>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Team to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Team from a ByteBuffer. */
  public static Team fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Integer id;
  @Deprecated public java.lang.CharSequence logo;
  @Deprecated public java.lang.CharSequence name;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Team() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param logo The new value for logo
   * @param name The new value for name
   */
  public Team(java.lang.Integer id, java.lang.CharSequence logo, java.lang.CharSequence name) {
    this.id = id;
    this.logo = logo;
    this.name = name;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return logo;
    case 2: return name;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: logo = (java.lang.CharSequence)value$; break;
    case 2: name = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'logo' field.
   * @return The value of the 'logo' field.
   */
  public java.lang.CharSequence getLogo() {
    return logo;
  }

  /**
   * Sets the value of the 'logo' field.
   * @param value the value to set.
   */
  public void setLogo(java.lang.CharSequence value) {
    this.logo = value;
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Creates a new Team RecordBuilder.
   * @return A new Team RecordBuilder
   */
  public static com.newyeti.common.avro.schema.Team.Builder newBuilder() {
    return new com.newyeti.common.avro.schema.Team.Builder();
  }

  /**
   * Creates a new Team RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Team RecordBuilder
   */
  public static com.newyeti.common.avro.schema.Team.Builder newBuilder(com.newyeti.common.avro.schema.Team.Builder other) {
    return new com.newyeti.common.avro.schema.Team.Builder(other);
  }

  /**
   * Creates a new Team RecordBuilder by copying an existing Team instance.
   * @param other The existing instance to copy.
   * @return A new Team RecordBuilder
   */
  public static com.newyeti.common.avro.schema.Team.Builder newBuilder(com.newyeti.common.avro.schema.Team other) {
    return new com.newyeti.common.avro.schema.Team.Builder(other);
  }

  /**
   * RecordBuilder for Team instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Team>
    implements org.apache.avro.data.RecordBuilder<Team> {

    private java.lang.Integer id;
    private java.lang.CharSequence logo;
    private java.lang.CharSequence name;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.newyeti.common.avro.schema.Team.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.logo)) {
        this.logo = data().deepCopy(fields()[1].schema(), other.logo);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.name)) {
        this.name = data().deepCopy(fields()[2].schema(), other.name);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Team instance
     * @param other The existing instance to copy.
     */
    private Builder(com.newyeti.common.avro.schema.Team other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.logo)) {
        this.logo = data().deepCopy(fields()[1].schema(), other.logo);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.name)) {
        this.name = data().deepCopy(fields()[2].schema(), other.name);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.newyeti.common.avro.schema.Team.Builder setId(java.lang.Integer value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.newyeti.common.avro.schema.Team.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'logo' field.
      * @return The value.
      */
    public java.lang.CharSequence getLogo() {
      return logo;
    }

    /**
      * Sets the value of the 'logo' field.
      * @param value The value of 'logo'.
      * @return This builder.
      */
    public com.newyeti.common.avro.schema.Team.Builder setLogo(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.logo = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'logo' field has been set.
      * @return True if the 'logo' field has been set, false otherwise.
      */
    public boolean hasLogo() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'logo' field.
      * @return This builder.
      */
    public com.newyeti.common.avro.schema.Team.Builder clearLogo() {
      logo = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public com.newyeti.common.avro.schema.Team.Builder setName(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public com.newyeti.common.avro.schema.Team.Builder clearName() {
      name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Team build() {
      try {
        Team record = new Team();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.logo = fieldSetFlags()[1] ? this.logo : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.name = fieldSetFlags()[2] ? this.name : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Team>
    WRITER$ = (org.apache.avro.io.DatumWriter<Team>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Team>
    READER$ = (org.apache.avro.io.DatumReader<Team>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
