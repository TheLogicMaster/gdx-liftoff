@file:Suppress("unused") // Extension classes accessed via reflection.

package gdx.liftoff.data.libraries.unofficial

import com.badlogic.gdx.Gdx
import gdx.liftoff.data.libraries.Repository
import gdx.liftoff.data.libraries.camelCaseToKebabCase
import gdx.liftoff.data.libraries.official.Ashley
import gdx.liftoff.data.libraries.official.Box2D
import gdx.liftoff.data.libraries.official.Freetype
import gdx.liftoff.data.platforms.Core
import gdx.liftoff.data.project.Project
import gdx.liftoff.views.Extension
import gdx.liftoff.views.fetchVersionFromMavenCentral
import khttp.get

/**
 * Modular Kotlin utilities.
 * @author libKTX organization
 */
abstract class KtxExtension : ThirdPartyExtension() {
    override val defaultVersion = "1.10.0-b4"
    override val repository = Repository.KTX
    override val group = "io.github.libktx"
    override val name
        get() = id.camelCaseToKebabCase()
}

val latestKtxVersion by lazy {
    // Fetching and caching KTX version from the repo:
    try {
        get("https://raw.githubusercontent.com/libktx/ktx/master/version.txt").content.decodeToString().trim()
    } catch (exception: Exception) {
        Gdx.app.error("gdx-liftoff", "Unable to fetch KTX version from the repository.", exception)
        fetchVersionFromMavenCentral(KtxActors())
    }
}

/**
 * Kotlin utilities for Scene2D actors API.
 */
@Extension
class KtxActors : KtxExtension() {
    override val id = "ktxActors"
    override val url = "https://github.com/libktx/ktx/tree/master/actors"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * General application listener utilities for Kotlin applications.
 */
@Extension
class KtxApp : KtxExtension() {
    override val id = "ktxApp"
    override val url = "https://github.com/libktx/ktx/tree/master/app"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Utilities for Ashley entity component system.
 */
@Extension
class KtxAshley : KtxExtension() {
    override val id = "ktxAshley"
    override val url = "https://github.com/libktx/ktx/tree/master/ashley"

    override fun initiateDependencies(project: Project) {
        Ashley().initiate(project)

        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for assets management.
 */
@Extension
class KtxAssets : KtxExtension() {
    override val id = "ktxAssets"
    override val url = "https://github.com/libktx/ktx/tree/master/assets"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for asynchronous assets management.
 */
@Extension
class KtxAssetsAsync : KtxExtension() {
    override val id = "ktxAssetsAsync"
    override val url = "https://github.com/libktx/ktx/tree/master/assets-async"

    override fun initiateDependencies(project: Project) {
        KtxAssets().initiate(project)
        KtxAsync().initiate(project)
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin coroutines context and utilities for asynchronous operations.
 */
@Extension
class KtxAsync : KtxExtension() {
    override val id = "ktxAsync"
    override val url = "https://github.com/libktx/ktx/tree/master/async"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin Box2D utilities and type-safe builders.
 */
@Extension
class KtxBox2D : KtxExtension() {
    override val id = "ktxBox2d"
    override val url = "https://github.com/libktx/ktx/tree/master/box2d"

    override fun initiateDependencies(project: Project) {
        Box2D().initiate(project)

        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for libGDX collections.
 */
@Extension
class KtxCollections : KtxExtension() {
    override val id = "ktxCollections"
    override val url = "https://github.com/libktx/ktx/tree/master/collections"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for loading TrueType fonts via Freetype.
 */
@Extension
class KtxFreetype : KtxExtension() {
    override val id = "ktxFreetype"
    override val url = "https://github.com/libktx/ktx/tree/master/freetype"

    override fun initiateDependencies(project: Project) {
        Freetype().initiate(project)
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for asynchronous loading of TrueType fonts via Freetype.
 */
@Extension
class KtxFreetypeAsync : KtxExtension() {
    override val id = "ktxFreetypeAsync"
    override val url = "https://github.com/libktx/ktx/tree/master/freetype-aync"

    override fun initiateDependencies(project: Project) {
        KtxFreetype().initiate(project)
        KtxAsync().initiate(project)
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for handling graphics in libGDX applications.
 */
@Extension
class KtxGraphics : KtxExtension() {
    override val id = "ktxGraphics"
    override val url = "https://github.com/libktx/ktx/tree/master/graphics"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for internationalization.
 */
@Extension
class KtxI18n : KtxExtension() {
    override val id = "ktxI18n"
    override val url = "https://github.com/libktx/ktx/tree/master/i18n"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin dependency injection without reflection usage.
 */
@Extension
class KtxInject : KtxExtension() {
    override val id = "ktxInject"
    override val url = "https://github.com/libktx/ktx/tree/master/inject"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * libGDX JSON serialization utilities for Kotlin applications.
 */
@Extension
class KtxJson : KtxExtension() {
    override val id = "ktxJson"
    override val url = "https://github.com/libktx/ktx/tree/master/collections"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for zero-overhead logging.
 */
@Extension
class KtxLog : KtxExtension() {
    override val id = "ktxLog"
    override val url = "https://github.com/libktx/ktx/tree/master/log"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin utilities for math-related classes.
 */
@Extension
class KtxMath : KtxExtension() {
    override val id = "ktxMath"
    override val url = "https://github.com/libktx/ktx/tree/master/math"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * libGDX preferences utilities for applications developed with Kotlin.
 */
@Extension
class KtxPreferences : KtxExtension() {
    override val id = "ktxPreferences"
    override val url = "https://github.com/libktx/ktx/tree/master/preferences"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * libGDX reflection utilities for applications developed with Kotlin.
 */
@Extension
class KtxReflect : KtxExtension() {
    override val id = "ktxReflect"
    override val url = "https://github.com/libktx/ktx/tree/master/reflect"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin type-safe builders for Scene2D GUI.
 */
@Extension
class KtxScene2D : KtxExtension() {
    override val id = "ktxScene2d"
    override val url = "https://github.com/libktx/ktx/tree/master/scene2d"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin type-safe builders for Scene2D widget styles.
 */
@Extension
class KtxStyle : KtxExtension() {
    override val id = "ktxStyle"
    override val url = "https://github.com/libktx/ktx/tree/master/style"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Tiled utilities for libGDX applications written with Kotlin.
 */
@Extension
class KtxTiled : KtxExtension() {
    override val id = "ktxTiled"
    override val url = "https://github.com/libktx/ktx/tree/master/tiled"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin type-safe builders for VisUI widgets.
 */
@Extension
class KtxVis : KtxExtension() {
    override val id = "ktxVis"
    override val url = "https://github.com/libktx/ktx/tree/master/vis"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}

/**
 * Kotlin type-safe builders for VisUI widget styles.
 */
@Extension
class KtxVisStyle : KtxExtension() {
    override val id = "ktxVisStyle"
    override val url = "https://github.com/libktx/ktx/tree/master/vis-style"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "$group:$name")
    }
}