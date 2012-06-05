AndroidUI
=========

UI bits and pieces

Texture (and TextUreExample) allow repeating bitmap (texture) in a ShapeDrawable. 
Annoying, when a Shape is created via xml, a ShapeDrawble is not instantiated but a GradientDrawable.
Furthermore, the Android framework does not allow new types of Drawable to be defined in xml.