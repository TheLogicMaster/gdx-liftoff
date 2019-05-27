package com.github.czyzby.setup.data.platforms

import com.github.czyzby.setup.data.files.CopiedFile
import com.github.czyzby.setup.data.files.path
import com.github.czyzby.setup.data.gradle.GradleFile
import com.github.czyzby.setup.data.project.Project

/**
 * Common interface for all supported platforms. Implementation should be annotated with GdxPlatform.
 * @author MJ
 */
interface Platform {
    /**
     * Unique ID of the platform.
     */
    val id: String

    /**
     * This value is set to true if the platform is a standard graphical LibGDX backend. False otherwise.
     */
    val isGraphical: Boolean
        get() = true

    /**
     * Creates a new gradle file used to manage this project's dependencies.
     * @param project requests the creation of file.
     */
    fun createGradleFile(project: Project): GradleFile

    /**
     * This method is used to resolve additional dependencies in other projects.
     * @param project contains the platform.
     */
    fun initiate(project: Project)

    fun addCopiedFile(project: Project, vararg file: String) {
        val originalFile = arrayOf("generator", id) + file
        project.files.add(CopiedFile(projectName = id, original = path(*originalFile), path = path(*file)))
    }

    fun addGradleTaskDescription(project: Project, task: String, description: String) {
        project.addGradleTaskDescription("$id:$task", description)
    }
}
