# Android Performance Testing

For this app, you'll work with the Pokemon API Sprint Challenge application and add some performance testing to it.

## Steps

1. Open the provided Pokemon API solution

2. Refactor the app to use Retrofit and OkHttp for its network calls
> You'll need to read the docs and rely on Google to do this. Keep last week's architectural lesson in mind when performing this refactor.

3. Add a custom Application class to the code

4. Add Stetho to your app
	1. Add Stetho to Gradle
	2. Add the Stetho OkHttp interceptor to Gradle
	3. Initialize Stetho in your application
	4. Add an interceptor to your Retrofit / OkHttp call from step 2

5. Spend some time examining the Stetho reports in Google Chrome
	1. Network activity
	2. UI
	3. Layout
	4. Memory
	5. Etc

6. Examine network activity in the Network Profiler

7. Examine memory usage in the Memory Profiler

8. Add LeakCanary to your app
	1. Introduce an intentional memory leak by displaying a dialog and rotating the emulator or device
	2. Does LeakCanary catch your leak?
	3. How would you prevent LeakCanary from appearing in production / release builds? 

#### Go Further

Create a UI element that's not eligible for garbage collection -- something like a dialog on a static reference
