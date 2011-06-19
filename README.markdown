BeeCrypt
========

BeeCrypt is a super-light-weight Scalifying wrapper around Damien Miller's fabulous [jBCrypt](http://www.mindrot.org/projects/jBCrypt/ "jBCrypt") - a solid Blowfish password hasher.

Usage is Ÿber-simple:

    import org.beecrypt.BeeCrypt._

    /**
    *   With default settings, Java's SecureRandom is used, and salt is re-salted 2^10 times
    */
    val hash = hashPassword("password123")

    /**
    *  Or you could go paranoid and re-salt a LOT
    */
    val awesomelySecureHash = hashPassword("password123", PrettyInsane)

    /**
    *  You can even supply your own salt generator. Make sure you know what you are doing.
    */
    val homeMadeHighGrainHash = hashPassword("password123", RatherTough, (x: Int) => homeMadeHighGrainSalt(x))

    /**
    *   And check 'em:
    */
    if  (matches("password123", hash)) letThemIn{() => happilyEverAfter}

For now, installation is old-school - get [SBT](http://code.google.com/p/simple-build-tool, "Scala Build Tool") if needed, clone this repo, build and install.
I'm working on getting this on [Scala Tools](http://scala-tools.org, "Scala Tools").




