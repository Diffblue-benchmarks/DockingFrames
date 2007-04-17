package bibliothek.gui.dock.util;

/**
 * A wrapper for a value which is either read from {@link DockProperties},
 * or can be set by the client directly.
 * @author Benjamin Sigg
 *
 * @param <A> the type of wrapper value
 */
public abstract class PropertyValue<A> {
	/** the access to the value */
	private PropertyKey<A> key;
	/** the observed properties, can be <code>null</code> */
	private DockProperties properties;
	/** the value set by the client */
	private A value;
	/** a listener to {@link #properties} */
	private DockPropertyListener<A> listener;
	
	/**
	 * Creates a new value.
	 * @param key the key used to access the value in {@link DockProperties}
	 */
	public PropertyValue( PropertyKey<A> key ){
		if( key == null )
			throw new IllegalArgumentException( "Key must not be null" );
		this.key = key;
		
		listener = new DockPropertyListener<A>(){
			public void propertyChanged( DockProperties properties, PropertyKey<A> property, A oldValue, A newValue ){
				if( value == null )
					valueChanged( oldValue, newValue );
			}
		};
	}
	
	/**
	 * Sets the {@link DockProperties} which should be observed.
	 * @param properties the new properties, can be <code>null</code>
	 */
	public void setProperties( DockProperties properties ){
		if( value != null )
			this.properties = properties;
		else{
			A oldValue = getValue();
			
			if( this.properties != null )
				this.properties.removeListener( key, listener );
			
			this.properties = properties;
			
			if( properties != null )
				properties.addListener( key, listener );
			
			A newValue = getValue();
			if( (oldValue == null && newValue != null) ||
				(oldValue != null && newValue == null) ||
				(oldValue != null && !oldValue.equals( newValue ))){
				
				valueChanged( oldValue, newValue );
			}
		}
	}
	
	/**
	 * Gets the currently observed properties.
	 * @return the map, or <code>null</code>
	 */
	public DockProperties getProperties(){
		return properties;
	}
	
	/**
	 * Gest the key which is used to access the value in {@link DockProperties}.
	 * @return the key
	 */
	public PropertyKey<A> getKey(){
		return key;
	}
	
	/**
	 * Gets the current value. The result is the argument of {@link #setValue(Object)} if
	 * the argument was not <code>null</code>, or else the value read from
	 * the {@link #setProperties(DockProperties) properties}. 
	 * @return the value or <code>null</code> if no value was found at all
	 */
	public A getValue(){
		if( value != null )
			return value;
		
		if( properties != null )
			return properties.get( key );
		
		return null;
	}
	
	/**
	 * Sets the current value.
	 * @param value the value, <code>null</code> if the value should be read
	 * from the {@link #setProperties(DockProperties) properties}
	 */
	public void setValue( A value ){
		if( properties != null ){
			if( this.value == null && value != null )
				properties.removeListener( key, listener );
			else if( this.value != null && value == null )
				properties.addListener( key, listener );
		}
		
		A oldValue = getValue();
		this.value = value;
		A newValue = getValue();
		
		if( (oldValue == null && newValue != null) ||
			(oldValue != null && newValue == null) ||
			(oldValue != null && !oldValue.equals( newValue ))){
				
			valueChanged( oldValue, newValue );
		}
	}
	
	/**
	 * Invoked when the value has been changed.
	 * @param oldValue the new value
	 * @param newValue the old value
	 */
	protected abstract void valueChanged( A oldValue, A newValue );
}