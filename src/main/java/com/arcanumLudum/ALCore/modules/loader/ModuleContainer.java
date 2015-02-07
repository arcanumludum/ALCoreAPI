package com.arcanumLudum.ALCore.modules.loader;

import java.io.File;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;

public interface ModuleContainer
{
    /**
     * The globally unique modid for this mod
     */
    String getModId();

    /**
     * A human readable name
     */

    String getName();

    /**
     * A human readable version identifier
     */
    String getVersion();

    /**
     * The location on the file system which this mod came from
     */
    File getSource();

    /**
     * A representative string encapsulating the sorting preferences for this
     * mod
     */
    String getSortingRules();

    /**
     * Register the event bus for the mod and the controller for error handling
     * Returns if this bus was successfully registered - disabled mods and other
     * mods that don't need real events should return false and avoid further
     * processing
     *
     * @param bus
     * @param controller
     */

    /**
     * Does this mod match the supplied mod
     *
     * @param mod
     */
    boolean matches(Object mod);

    /**
     * Get the actual mod object
     */
    Object getMod();

    boolean isImmutable();

    boolean isNetworkMod();

    String getDisplayVersion();

    Certificate getSigningCertificate();

    public static final Map<String,String> EMPTY_PROPERTIES = ImmutableMap.of();
    Map<String,String> getCustomModProperties();

    public Class<?> getCustomResourcePackClass();

    Map<String, String> getSharedModDescriptor();
}
