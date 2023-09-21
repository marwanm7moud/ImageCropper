package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final ComposeLibraryAccessors laccForComposeLibraryAccessors = new ComposeLibraryAccessors(owner);
    private final GoogleLibraryAccessors laccForGoogleLibraryAccessors = new GoogleLibraryAccessors(owner);
    private final KoinLibraryAccessors laccForKoinLibraryAccessors = new KoinLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final KotlinxLibraryAccessors laccForKotlinxLibraryAccessors = new KotlinxLibraryAccessors(owner);
    private final VoyagerLibraryAccessors laccForVoyagerLibraryAccessors = new VoyagerLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of libraries at androidx
     */
    public AndroidxLibraryAccessors getAndroidx() {
        return laccForAndroidxLibraryAccessors;
    }

    /**
     * Returns the group of libraries at compose
     */
    public ComposeLibraryAccessors getCompose() {
        return laccForComposeLibraryAccessors;
    }

    /**
     * Returns the group of libraries at google
     */
    public GoogleLibraryAccessors getGoogle() {
        return laccForGoogleLibraryAccessors;
    }

    /**
     * Returns the group of libraries at koin
     */
    public KoinLibraryAccessors getKoin() {
        return laccForKoinLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlin
     */
    public KotlinLibraryAccessors getKotlin() {
        return laccForKotlinLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlinx
     */
    public KotlinxLibraryAccessors getKotlinx() {
        return laccForKotlinxLibraryAccessors;
    }

    /**
     * Returns the group of libraries at voyager
     */
    public VoyagerLibraryAccessors getVoyager() {
        return laccForVoyagerLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityLibraryAccessors laccForAndroidxActivityLibraryAccessors = new AndroidxActivityLibraryAccessors(owner);
        private final AndroidxCoreLibraryAccessors laccForAndroidxCoreLibraryAccessors = new AndroidxCoreLibraryAccessors(owner);
        private final AndroidxUiLibraryAccessors laccForAndroidxUiLibraryAccessors = new AndroidxUiLibraryAccessors(owner);

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for appcompat (androidx.appcompat:appcompat)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAppcompat() {
                return create("androidx.appcompat");
        }

            /**
             * Creates a dependency provider for constraint (androidx.constraintlayout:constraintlayout-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getConstraint() {
                return create("androidx.constraint");
        }

            /**
             * Creates a dependency provider for foundation (androidx.compose.foundation:foundation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getFoundation() {
                return create("androidx.foundation");
        }

            /**
             * Creates a dependency provider for material3 (androidx.compose.material3:material3)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMaterial3() {
                return create("androidx.material3");
        }

        /**
         * Returns the group of libraries at androidx.activity
         */
        public AndroidxActivityLibraryAccessors getActivity() {
            return laccForAndroidxActivityLibraryAccessors;
        }

        /**
         * Returns the group of libraries at androidx.core
         */
        public AndroidxCoreLibraryAccessors getCore() {
            return laccForAndroidxCoreLibraryAccessors;
        }

        /**
         * Returns the group of libraries at androidx.ui
         */
        public AndroidxUiLibraryAccessors getUi() {
            return laccForAndroidxUiLibraryAccessors;
        }

    }

    public static class AndroidxActivityLibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityComposeLibraryAccessors laccForAndroidxActivityComposeLibraryAccessors = new AndroidxActivityComposeLibraryAccessors(owner);

        public AndroidxActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.activity.compose
         */
        public AndroidxActivityComposeLibraryAccessors getCompose() {
            return laccForAndroidxActivityComposeLibraryAccessors;
        }

    }

    public static class AndroidxActivityComposeLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxActivityCompose1LibraryAccessors laccForAndroidxActivityCompose1LibraryAccessors = new AndroidxActivityCompose1LibraryAccessors(owner);

        public AndroidxActivityComposeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.activity:activity-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("androidx.activity.compose");
        }

        /**
         * Returns the group of libraries at androidx.activity.compose.1
         */
        public AndroidxActivityCompose1LibraryAccessors get1() {
            return laccForAndroidxActivityCompose1LibraryAccessors;
        }

    }

    public static class AndroidxActivityCompose1LibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityCompose17LibraryAccessors laccForAndroidxActivityCompose17LibraryAccessors = new AndroidxActivityCompose17LibraryAccessors(owner);

        public AndroidxActivityCompose1LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.activity.compose.1.7
         */
        public AndroidxActivityCompose17LibraryAccessors get7() {
            return laccForAndroidxActivityCompose17LibraryAccessors;
        }

    }

    public static class AndroidxActivityCompose17LibraryAccessors extends SubDependencyFactory {

        public AndroidxActivityCompose17LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for 1 (androidx.activity:activity-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> get1() {
                return create("androidx.activity.compose.1.7.1");
        }

    }

    public static class AndroidxCoreLibraryAccessors extends SubDependencyFactory {
        private final AndroidxCoreKtxLibraryAccessors laccForAndroidxCoreKtxLibraryAccessors = new AndroidxCoreKtxLibraryAccessors(owner);

        public AndroidxCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.core.ktx
         */
        public AndroidxCoreKtxLibraryAccessors getKtx() {
            return laccForAndroidxCoreKtxLibraryAccessors;
        }

    }

    public static class AndroidxCoreKtxLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxCoreKtx1LibraryAccessors laccForAndroidxCoreKtx1LibraryAccessors = new AndroidxCoreKtx1LibraryAccessors(owner);

        public AndroidxCoreKtxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.core:core-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("androidx.core.ktx");
        }

        /**
         * Returns the group of libraries at androidx.core.ktx.1
         */
        public AndroidxCoreKtx1LibraryAccessors get1() {
            return laccForAndroidxCoreKtx1LibraryAccessors;
        }

    }

    public static class AndroidxCoreKtx1LibraryAccessors extends SubDependencyFactory {
        private final AndroidxCoreKtx110LibraryAccessors laccForAndroidxCoreKtx110LibraryAccessors = new AndroidxCoreKtx110LibraryAccessors(owner);

        public AndroidxCoreKtx1LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.core.ktx.1.10
         */
        public AndroidxCoreKtx110LibraryAccessors get10() {
            return laccForAndroidxCoreKtx110LibraryAccessors;
        }

    }

    public static class AndroidxCoreKtx110LibraryAccessors extends SubDependencyFactory {

        public AndroidxCoreKtx110LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for 1 (androidx.core:core-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> get1() {
                return create("androidx.core.ktx.1.10.1");
        }

    }

    public static class AndroidxUiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxUiToolingLibraryAccessors laccForAndroidxUiToolingLibraryAccessors = new AndroidxUiToolingLibraryAccessors(owner);

        public AndroidxUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ui (androidx.compose.ui:ui)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("androidx.ui");
        }

            /**
             * Creates a dependency provider for text (androidx.compose.ui:ui-text)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getText() {
                return create("androidx.ui.text");
        }

        /**
         * Returns the group of libraries at androidx.ui.tooling
         */
        public AndroidxUiToolingLibraryAccessors getTooling() {
            return laccForAndroidxUiToolingLibraryAccessors;
        }

    }

    public static class AndroidxUiToolingLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxUiToolingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for tooling (androidx.compose.ui:ui-tooling)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("androidx.ui.tooling");
        }

            /**
             * Creates a dependency provider for preview (androidx.compose.ui:ui-tooling-preview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPreview() {
                return create("androidx.ui.tooling.preview");
        }

    }

    public static class ComposeLibraryAccessors extends SubDependencyFactory {
        private final ComposeComponentsLibraryAccessors laccForComposeComponentsLibraryAccessors = new ComposeComponentsLibraryAccessors(owner);
        private final ComposeImageLibraryAccessors laccForComposeImageLibraryAccessors = new ComposeImageLibraryAccessors(owner);

        public ComposeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for foundation (org.jetbrains.compose.foundation:foundation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getFoundation() {
                return create("compose.foundation");
        }

            /**
             * Creates a dependency provider for maertialIcons (org.jetbrains.compose.material:material-icons-extended)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMaertialIcons() {
                return create("compose.maertialIcons");
        }

            /**
             * Creates a dependency provider for material3 (org.jetbrains.compose.material3:material3)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMaterial3() {
                return create("compose.material3");
        }

            /**
             * Creates a dependency provider for preview (org.jetbrains.compose.ui:ui-tooling-preview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPreview() {
                return create("compose.preview");
        }

            /**
             * Creates a dependency provider for runtime (org.jetbrains.compose.runtime:runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() {
                return create("compose.runtime");
        }

        /**
         * Returns the group of libraries at compose.components
         */
        public ComposeComponentsLibraryAccessors getComponents() {
            return laccForComposeComponentsLibraryAccessors;
        }

        /**
         * Returns the group of libraries at compose.image
         */
        public ComposeImageLibraryAccessors getImage() {
            return laccForComposeImageLibraryAccessors;
        }

    }

    public static class ComposeComponentsLibraryAccessors extends SubDependencyFactory {

        public ComposeComponentsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for resources (org.jetbrains.compose.components:components-resources)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getResources() {
                return create("compose.components.resources");
        }

    }

    public static class ComposeImageLibraryAccessors extends SubDependencyFactory {

        public ComposeImageLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for loader (io.github.qdsfdhvh:image-loader)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLoader() {
                return create("compose.image.loader");
        }

    }

    public static class GoogleLibraryAccessors extends SubDependencyFactory {

        public GoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for accompanist (com.google.accompanist:accompanist-systemuicontroller)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAccompanist() {
                return create("google.accompanist");
        }

    }

    public static class KoinLibraryAccessors extends SubDependencyFactory {

        public KoinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (io.insert-koin:koin-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() {
                return create("koin.android");
        }

            /**
             * Creates a dependency provider for compose (io.insert-koin:koin-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() {
                return create("koin.compose");
        }

            /**
             * Creates a dependency provider for core (io.insert-koin:koin-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("koin.core");
        }

            /**
             * Creates a dependency provider for test (io.insert-koin:koin-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() {
                return create("koin.test");
        }

    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for coroutines (org.jetbrains.kotlinx:kotlinx-coroutines-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCoroutines() {
                return create("kotlin.coroutines");
        }

    }

    public static class KotlinxLibraryAccessors extends SubDependencyFactory {

        public KotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for datetime (org.jetbrains.kotlinx:kotlinx-datetime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDatetime() {
                return create("kotlinx.datetime");
        }

    }

    public static class VoyagerLibraryAccessors extends SubDependencyFactory {
        private final VoyagerBottomsheetLibraryAccessors laccForVoyagerBottomsheetLibraryAccessors = new VoyagerBottomsheetLibraryAccessors(owner);
        private final VoyagerTabLibraryAccessors laccForVoyagerTabLibraryAccessors = new VoyagerTabLibraryAccessors(owner);

        public VoyagerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for androidx (cafe.adriel.voyager:voyager-androidx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroidx() {
                return create("voyager.androidx");
        }

            /**
             * Creates a dependency provider for koin (cafe.adriel.voyager:voyager-koin)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKoin() {
                return create("voyager.koin");
        }

            /**
             * Creates a dependency provider for navigator (cafe.adriel.voyager:voyager-navigator)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getNavigator() {
                return create("voyager.navigator");
        }

            /**
             * Creates a dependency provider for transitions (cafe.adriel.voyager:voyager-transitions)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTransitions() {
                return create("voyager.transitions");
        }

        /**
         * Returns the group of libraries at voyager.bottomsheet
         */
        public VoyagerBottomsheetLibraryAccessors getBottomsheet() {
            return laccForVoyagerBottomsheetLibraryAccessors;
        }

        /**
         * Returns the group of libraries at voyager.tab
         */
        public VoyagerTabLibraryAccessors getTab() {
            return laccForVoyagerTabLibraryAccessors;
        }

    }

    public static class VoyagerBottomsheetLibraryAccessors extends SubDependencyFactory {

        public VoyagerBottomsheetLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for navigator (cafe.adriel.voyager:voyager-bottom-sheet-navigator)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getNavigator() {
                return create("voyager.bottomsheet.navigator");
        }

    }

    public static class VoyagerTabLibraryAccessors extends SubDependencyFactory {

        public VoyagerTabLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for navigator (cafe.adriel.voyager:voyager-tab-navigator)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getNavigator() {
                return create("voyager.tab.navigator");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final AccompanistVersionAccessors vaccForAccompanistVersionAccessors = new AccompanistVersionAccessors(providers, config);
        private final AndroidxVersionAccessors vaccForAndroidxVersionAccessors = new AndroidxVersionAccessors(providers, config);
        private final ComposeVersionAccessors vaccForComposeVersionAccessors = new ComposeVersionAccessors(providers, config);
        private final ConstraintVersionAccessors vaccForConstraintVersionAccessors = new ConstraintVersionAccessors(providers, config);
        private final CoroutineVersionAccessors vaccForCoroutineVersionAccessors = new CoroutineVersionAccessors(providers, config);
        private final HiltVersionAccessors vaccForHiltVersionAccessors = new HiltVersionAccessors(providers, config);
        private final ImageVersionAccessors vaccForImageVersionAccessors = new ImageVersionAccessors(providers, config);
        private final IosVersionAccessors vaccForIosVersionAccessors = new IosVersionAccessors(providers, config);
        private final KoinVersionAccessors vaccForKoinVersionAccessors = new KoinVersionAccessors(providers, config);
        private final VoyagerVersionAccessors vaccForVoyagerVersionAccessors = new VoyagerVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: activityCompose (1.7.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getActivityCompose() { return getVersion("activityCompose"); }

            /**
             * Returns the version associated to this alias: androidGradlePlugin (7.4.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidGradlePlugin() { return getVersion("androidGradlePlugin"); }

            /**
             * Returns the version associated to this alias: compileSdk (34)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompileSdk() { return getVersion("compileSdk"); }

            /**
             * Returns the version associated to this alias: coreKtx (1.12.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCoreKtx() { return getVersion("coreKtx"); }

            /**
             * Returns the version associated to this alias: foundation (1.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFoundation() { return getVersion("foundation"); }

            /**
             * Returns the version associated to this alias: jvmToolchain (11)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJvmToolchain() { return getVersion("jvmToolchain"); }

            /**
             * Returns the version associated to this alias: kotlin (1.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlin() { return getVersion("kotlin"); }

            /**
             * Returns the version associated to this alias: minSdk (24)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMinSdk() { return getVersion("minSdk"); }

            /**
             * Returns the version associated to this alias: targetSdk (34)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTargetSdk() { return getVersion("targetSdk"); }

            /**
             * Returns the version associated to this alias: ui (1.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getUi() { return getVersion("ui"); }

        /**
         * Returns the group of versions at versions.accompanist
         */
        public AccompanistVersionAccessors getAccompanist() {
            return vaccForAccompanistVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.androidx
         */
        public AndroidxVersionAccessors getAndroidx() {
            return vaccForAndroidxVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.compose
         */
        public ComposeVersionAccessors getCompose() {
            return vaccForComposeVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.constraint
         */
        public ConstraintVersionAccessors getConstraint() {
            return vaccForConstraintVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.coroutine
         */
        public CoroutineVersionAccessors getCoroutine() {
            return vaccForCoroutineVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.hilt
         */
        public HiltVersionAccessors getHilt() {
            return vaccForHiltVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.image
         */
        public ImageVersionAccessors getImage() {
            return vaccForImageVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.ios
         */
        public IosVersionAccessors getIos() {
            return vaccForIosVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.koin
         */
        public KoinVersionAccessors getKoin() {
            return vaccForKoinVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.voyager
         */
        public VoyagerVersionAccessors getVoyager() {
            return vaccForVoyagerVersionAccessors;
        }

    }

    public static class AccompanistVersionAccessors extends VersionFactory  {

        public AccompanistVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: accompanist.version (0.30.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("accompanist.version"); }

    }

    public static class AndroidxVersionAccessors extends VersionFactory  {

        private final AndroidxActivityVersionAccessors vaccForAndroidxActivityVersionAccessors = new AndroidxActivityVersionAccessors(providers, config);
        public AndroidxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.appcompat (1.6.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAppcompat() { return getVersion("androidx.appcompat"); }

            /**
             * Returns the version associated to this alias: androidx.core (1.12.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("androidx.core"); }

        /**
         * Returns the group of versions at versions.androidx.activity
         */
        public AndroidxActivityVersionAccessors getActivity() {
            return vaccForAndroidxActivityVersionAccessors;
        }

    }

    public static class AndroidxActivityVersionAccessors extends VersionFactory  {

        public AndroidxActivityVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.activity.compose (1.7.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompose() { return getVersion("androidx.activity.compose"); }

    }

    public static class ComposeVersionAccessors extends VersionFactory  {

        public ComposeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: compose.version (1.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("compose.version"); }

    }

    public static class ConstraintVersionAccessors extends VersionFactory  {

        public ConstraintVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: constraint.version (1.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("constraint.version"); }

    }

    public static class CoroutineVersionAccessors extends VersionFactory  {

        public CoroutineVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: coroutine.version (1.7.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("coroutine.version"); }

    }

    public static class HiltVersionAccessors extends VersionFactory  {

        public HiltVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: hilt.version (2.46.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("hilt.version"); }

    }

    public static class ImageVersionAccessors extends VersionFactory  {

        public ImageVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: image.loader (1.6.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLoader() { return getVersion("image.loader"); }

    }

    public static class IosVersionAccessors extends VersionFactory  {

        public IosVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: ios.deploymentTarget (14.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDeploymentTarget() { return getVersion("ios.deploymentTarget"); }

    }

    public static class KoinVersionAccessors extends VersionFactory  {

        public KoinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: koin.version (3.4.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("koin.version"); }

    }

    public static class VoyagerVersionAccessors extends VersionFactory  {

        public VoyagerVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: voyager.version (1.0.0-rc06)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("voyager.version"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

            /**
             * Creates a dependency bundle provider for voyager which is an aggregate for the following dependencies:
             * <ul>
             *    <li>cafe.adriel.voyager:voyager-navigator</li>
             *    <li>cafe.adriel.voyager:voyager-koin</li>
             *    <li>cafe.adriel.voyager:voyager-transitions</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getVoyager() {
                return createBundle("voyager");
            }

    }

    public static class PluginAccessors extends PluginFactory {
        private final AndroidPluginAccessors paccForAndroidPluginAccessors = new AndroidPluginAccessors(providers, config);
        private final JetbrainsPluginAccessors paccForJetbrainsPluginAccessors = new JetbrainsPluginAccessors(providers, config);
        private final KotlinPluginAccessors paccForKotlinPluginAccessors = new KotlinPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of plugins at plugins.android
         */
        public AndroidPluginAccessors getAndroid() {
            return paccForAndroidPluginAccessors;
        }

        /**
         * Returns the group of plugins at plugins.jetbrains
         */
        public JetbrainsPluginAccessors getJetbrains() {
            return paccForJetbrainsPluginAccessors;
        }

        /**
         * Returns the group of plugins at plugins.kotlin
         */
        public KotlinPluginAccessors getKotlin() {
            return paccForKotlinPluginAccessors;
        }

    }

    public static class AndroidPluginAccessors extends PluginFactory {

        public AndroidPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for android.application to the plugin id 'com.android.application'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getApplication() { return createPlugin("android.application"); }

            /**
             * Creates a plugin provider for android.library to the plugin id 'com.android.library'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getLibrary() { return createPlugin("android.library"); }

    }

    public static class JetbrainsPluginAccessors extends PluginFactory {

        public JetbrainsPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for jetbrains.compose to the plugin id 'org.jetbrains.compose'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getCompose() { return createPlugin("jetbrains.compose"); }

    }

    public static class KotlinPluginAccessors extends PluginFactory {
        private final KotlinNativePluginAccessors paccForKotlinNativePluginAccessors = new KotlinNativePluginAccessors(providers, config);

        public KotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.android to the plugin id 'org.jetbrains.kotlin.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroid() { return createPlugin("kotlin.android"); }

            /**
             * Creates a plugin provider for kotlin.multiplatform to the plugin id 'org.jetbrains.kotlin.multiplatform'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getMultiplatform() { return createPlugin("kotlin.multiplatform"); }

        /**
         * Returns the group of plugins at plugins.kotlin.native
         */
        public KotlinNativePluginAccessors getNative() {
            return paccForKotlinNativePluginAccessors;
        }

    }

    public static class KotlinNativePluginAccessors extends PluginFactory {

        public KotlinNativePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.native.cocoapods to the plugin id 'org.jetbrains.kotlin.native.cocoapods'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getCocoapods() { return createPlugin("kotlin.native.cocoapods"); }

    }

}
