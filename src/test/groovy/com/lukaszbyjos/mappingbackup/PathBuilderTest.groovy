package com.lukaszbyjos.mappingbackup

class PathBuilderTest extends GroovyTestCase {
    void testBuildPath() {
        MapBackExtension mapBackExtension = new MapBackExtension()
        mapBackExtension.path = "version_nope_name"
        def result = PathBuilder.buildPath(mapBackExtension, "root", 23, "1.0.4123")
        def expected = "root/deploy/23_nope_1.0.4123"
        if (System.properties['os.name'].toLowerCase().contains('windows')) {
            expected = expected.replaceAll("/", "\\\\")
        }
        assertEquals(expected, result)
    }
}
