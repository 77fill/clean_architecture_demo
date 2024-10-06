# Motivation
This simple project is meant to be a possible start of a school project that aims to be something similar to Kahoot. The focus lies on implementing Clean Architecture by Robert Martin. 

[Here is an illustrated explanation](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

# Build & Run
Build all subprojects by using maven in the top-level project

	mvn package
	
This creates .jar files in all subprojects and copies them to target/all_artifacts/

Then run:

	java -cp target/all_artifacts/* dev.pschmalz.clean_architecture_demo.Application