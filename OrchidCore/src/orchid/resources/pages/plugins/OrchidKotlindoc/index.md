---
official: true
description: Embed Kotlin and Java documentation in your Orchid site using Dokka.
images:
  - src: http://res.cloudinary.com/orchid/image/upload/c_scale,w_300,e_blur:150/v1524974952/plugins/javadoc.jpg
    alt: Javadoc
    caption: Photo by Brooke Lark on Unsplash
---

### Using Orchid with Dokka

The Orchid Kotlindoc plugin runs Dokka for you, using a formatter which outputs JSON. This makes it easy for Orchid to
convert data generated by Dokka into something that is easily usable from Orchid. Just include the plugin, point it to
the directories containing Kotlin or Java source files, and away you go!

Note: this plugin is still a work-in-progress, but is coming along nicely.