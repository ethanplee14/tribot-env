## About
This is a development environment for OSRS TRiBot. It's meant to allow easy tribot bot scripting by wrapping
various tribot functions into a behavioral tree nodes. Use BTBuilder to create behavioral tree or use util methods.

###Example
Simple woodcutting script. Doesn't include checking for an axe or dropping wood, but it conveys the idea. 
```
sequencer("Woodcut Sequence")
    .node(selector()
        .condition("Player In WCArea?", PlayerIn(locale.treeArea()))
        .action("DaxWalk WCArea", DaxWalk(locale.destTile()))
        .build()
    ).node(selector()
        .condition("Player Woodcutting?", PlayerAnimating(WOODCUTTING))
        .node(sequencer()
            .action("Chop Oak", Interact({ nearestObj(20, Filters.Objects.inArea(locale.treeArea())
                .and(Filters.Objects.nameEquals(*locale.treeNames()))::test) }, "Chop"))
            .action("Wait Woodcutting", WaitFor(PlayerAnimating(WOODCUTTING), 5000))
            .build()
        ).build()
    ).build().run()
```

###Libraries included: 
- behavioral-tree: Lightweight library to create behavioral trees in Kotlin
- tribot: Library for TRiBot. Wraps various tribot builds, actions and common conditions in a behavior tree nodes. 
  Also includes a BankCache, a simple Logger meant for the TRiBot console, and general utility methods.
- <a href="https://github.com/itsdax/Runescape-Web-Walker-Engine">dax-walker</a>: OSRS world traversing library. Created by Dax.
- <a href="https://gitlab.com/WBScripting/rs-item-services">item-services</a>: OSRS item price lookups. Allows lookup 
from GrandExchange, RSBuddy, and Runelite. Created by WastedBro


###Creating a Script
To create a new TRiBot script project, create a new directory in the `scripts` directory. Include a src folder for your 
src code, and then add the new script to the `settings.gradle.kts`. To import desired libraries to your new project, 
create a `build.gradle.kts` at your project's root then include dependencies configuration in the file. Check `wcfarm` 
for an example.

###Caveats
`libraries/tribot` is still immature. Will expand the library on an as need basis as more scripts are developed. 
