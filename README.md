✨ Compose Sparkle Border

A lightweight, highly customizable Jetpack Compose library that adds an animated, rotating gradient border to any UI component.

Perfect for Social Avatars, Premium Cards, Status Indicators, and Call-to-Action Buttons.

📱 Demo

<img src="demo.gif" width="300" alt="Sparkle Border Demo" />

🚀 Features

🌈 Flexible Gradients: Support both Linear (for rectangles) and Sweep (for circles) gradients.

⚡ High Performance: Built on standard Compose InfiniteTransition, zero recomposition overhead.

🎨 Smart Masking: Auto-calculates inner corner radius for RoundedCornerShape and CircleShape.

⏱️ Customizable Speed: Control the rotation duration.

📦 Plug & Play: Just one Modifier extension.

📦 Installation

Add the JitPack repository to your settings.gradle.kts:

    dependencyResolutionManagement {
        repositories {
            // ...
            maven { url = uri("https://jitpack.io") }
        }
    }



Add the dependency to your module's build.gradle.kts:

    dependencies {
        // Make sure your artifactId in build.gradle matches the repo name 'compose-sparkle-border'
        implementation("com.github.LotusReichhart:compose-sparkle-border:1.0.0")
    }


💻 Usage

1. The "Instagram" Avatar (Sweep Gradient)

Best for circular shapes.

    Box(
        modifier = Modifier
            .size(100.dp)
            .sparkleBorder(
                brush = Brush.sweepGradient(
                    listOf(Color(0xFFF9CE34), Color(0xFFEE2A7B), Color(0xFF6228D7))
                ),
                backgroundColor = Color.Black,
                shape = CircleShape,
                borderWidth = 4.dp
            )
            .clip(CircleShape)
    ) {
    // Your Image Composable here
    }


2. The "Premium" Card (Linear Gradient)

Best for Buttons or Cards.

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .sparkleBorder(
                brush = Brush.linearGradient(
                listOf(Color.Cyan, Color.Magenta)
                ),
                backgroundColor = Color(0xFF1E1E1E), // Match your container background
                shape = RoundedCornerShape(16.dp),
                borderWidth = 2.dp,
                animationDurationInMillis = 3000
            )
    ) {
    // Card Content
    }


⚙️ Customization

| Parameter                     | Type  | Default                   | Description                                                               |
| ----------------------------- | ----- | ------------------------- | ------------------------------------------------------------------------- |
| **brush**                     | Brush | Required                  | The gradient style (Linear, Sweep, Radial) used for the rotating border.  |
| **backgroundColor**           | Color | Required                  | The color of the inner container. Must match your component's background. |
| **shape**                     | Shape | RoundedCornerShape(12.dp) | The shape of the border. Supports Circle and RoundedRect.                 |
| **borderWidth**               | Dp    | 2.dp                      | Thickness of the border.                                                  |
| **animationDurationInMillis** | Int   | 2000                      | Duration of one full rotation (360 degrees).                              |


🤝 Contribution

Contributions are welcome! Fork the repo and submit a pull request.
