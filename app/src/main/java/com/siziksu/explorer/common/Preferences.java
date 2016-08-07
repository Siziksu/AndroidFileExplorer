package com.siziksu.explorer.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Class with methods to work with Shared Preferences.
 */
public final class Preferences {

    private static Preferences instance;
    private final Context context;

    private Preferences(Context context) {
        this.context = context;
    }

    /**
     * This class must be initialized before asking for an instance.
     *
     * @param context the context
     */
    public static void init(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }
    }

    /**
     * This method provides an instance of this class. First needs to be initialized.
     */
    public static Preferences get() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    /**
     * Registers a SharedPreferenceChangeListener.
     *
     * @param listener the listener to register
     */
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Unregisters a SharedPreferenceChangeListener.
     *
     * @param listener the listener to unregister
     */
    public void unregisterOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }

    private SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Sets a string value into the Shared Preferences.
     *
     * @param key   the key
     * @param value the value
     */
    public void setString(String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }

    /**
     * Gets a string value from the Shared Preferences.
     *
     * @param key          the key
     * @param defaultValue the value
     * @return the value for that key
     */
    public String getString(String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    /**
     * Sets a boolean value into the Shared Preferences.
     *
     * @param key   the key
     * @param value the value
     */
    public void setBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).commit();
    }

    /**
     * Gets a boolean value from the Shared Preferences.
     *
     * @param key          the key
     * @param defaultValue the value
     * @return the value for that key
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    /**
     * Sets an integer value into the Shared Preferences.
     *
     * @param key   the key
     * @param value the value
     */
    public void setInt(String key, int value) {
        getPreferences().edit().putInt(key, value).commit();
    }

    /**
     * Gets an integer value from the Shared Preferences.
     *
     * @param key          the key
     * @param defaultValue the value
     * @return the value for that key
     */
    public int getInt(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    /**
     * Sets a long value into the Shared Preferences.
     *
     * @param key   the key
     * @param value the value
     */
    public void setLong(String key, long value) {
        getPreferences().edit().putLong(key, value).commit();
    }

    /**
     * Gets a long value from the Shared Preferences.
     *
     * @param key          the key
     * @param defaultValue the value
     * @return the value for that key
     */
    public long getLong(String key, long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    /**
     * Sets a float value into the Shared Preferences.
     *
     * @param key   the key
     * @param value the value
     */
    public void setFloat(String key, float value) {
        getPreferences().edit().putFloat(key, value).commit();
    }

    /**
     * Gets a float value from the Shared Preferences.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the value for that key
     */
    public float getFloat(String key, float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    /**
     * Deletes a key from the Shared Preferences.
     *
     * @param key the key to delete
     */
    public void deleteKey(String key) {
        if (getPreferences().contains(key)) {
            getPreferences().edit().remove(key).commit();
        }
    }
}