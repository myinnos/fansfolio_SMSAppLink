# fansfolio_SMSAppLink

Kindly use the following links to use this library:

In build.gradle (Project)

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
And then in the other gradle file(may be your app gradle or your own module library gradle, but never add in both of them to avoid conflict.)
	
	 dependencies {
	        compile 'com.github.myinnos:smsapp_fansfolio:1.0'
	 }


