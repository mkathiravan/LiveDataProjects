# LiveDataProjects

LiveData is an observable data holder class which just observes the changes in the View layer and updates the view.
MutableLiveData is a subclass of LiveData which is used for some of it's properties (setValue/postValue) and using these properties we can easily notify the ui when onChange() is called.

But what exactly is the difference between ObservableField and LiveData?
ObservableField <T> is not Lifecycle aware but LiveData is. That means LiveData will only update app component observers that are in an active lifecycle state and not which are inactive.
We have to do manual handling of Lifecycle awareness in ObservableField.

What are the benefits of Using LiveData?
No memory Leaks : As the observers are bound to the lifecycle of the app, when not required the it is destroyed
No Crashes due to Stopped Activity : As the Activity is not in use, so is the LiveData. So the data streaming stops which stops the crashes in the back stack.
Configuration Changes : It handles the latest data when view is recreated due to screen rotation.
LiveData is just a data type which notifies it’s observer whenever the data is changed. LiveData is like a data changed notifier.

LiveData notifies the observer using setValue() and postValue().
setValue() runs on the main thread.
postValue() runs on the background thread.
Invoking getValue() on the LiveData type instance would return you the current data.


![Screenshot_20190827-233751](https://user-images.githubusercontent.com/39657409/63797081-ed5fe080-c924-11e9-90a5-2480b441edb3.jpg)

![Screenshot_20190827-233804](https://user-images.githubusercontent.com/39657409/63797099-fea8ed00-c924-11e9-8215-d0d792aff5ec.png)
