package com.lukaszbyjos.mappingbackup

class PathBuilder {
    static String buildPath(MapBackExtension mapBackExtension,
                            String projectRoot,
                            def versionCode,
                            def versionName) {
        def mainFolder = mapBackExtension.mainFolder
        def path = mapBackExtension.path
        path = path.replaceAll("version", String.valueOf(versionCode))
        path = path.replaceAll("name", String.valueOf(versionName))
        def finalPath = projectRoot + "/" + mainFolder + "/" + path
        if (System.properties['os.name'].toLowerCase().contains('windows')) {
            finalPath = finalPath.replaceAll("/", "\\\\")
        }
    }
}
