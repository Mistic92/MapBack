package com.lukaszbyjos.mappingbackup

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy

class MapBackPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create('mapback', MapBackExtension)

        project.afterEvaluate {

            def applicationVariants = null
            if (project.android.hasProperty('applicationVariants')) {
                applicationVariants = project.android.applicationVariants
            }

            applicationVariants.all { variant ->
                def apk
                String deployPath = PathBuilder.buildPath(project.mapback,
                        project.rootDir.path,
                        variant.versionCode,
                        variant.versionName)
                variant.outputs.each { output ->
                    if (output.outputFile?.name?.contains('release')) {
                        apk = output.outputFile
                    }
                }

                if (variant.getBuildType().isMinifyEnabled()) {
                    variant.assemble.doLast {
                        String mapFile = variant.mappingFile
                        project.getTasks().create("MapBack", Copy.class, new Action<Copy>() {
                            @Override
                            public void execute(Copy copy) {
                                copy.from(mapFile)
                                copy.from(apk)
                                copy.into(deployPath)
                            }
                        });
                        Task copyTask = project.getTasks().findByName("MapBack")
                        copyTask.getActions().each { it.execute(copyTask) }
                        if (project.mapback.autoOpen)
                            if (System.properties['os.name'].toLowerCase().contains('windows')) {
                                Runtime.getRuntime().exec("explorer.exe " + deployPath);
                            }
                    }
                }
            }

        }

    }
}

