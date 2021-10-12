# TempooNews

Is mobile App which show to user latest news and allow user to search for specific topics such as ("bitcoin", "football", ....), and also 
allow user to click on any news to see more information about news, finally if user wants to see source of news so user can click on view source button.


External Lib:
_____________

picasso:
	- Handling ImageView recycling and download cancelation in an adapter.
	- Complex image transformations with minimal memory use.
	- Automatic memory and disk caching.
- usage in app (in NewsList & NewsDetails screen) used to download image from repo and put it in imageView.
	

retrofit: 
	- use retrofit for network calls and makes downloading JSON or XML data from a web API straightforward. 
	- parse downloaded data into a Plain Old Java Object (POJO) which must be defined for each "resource" in the response.



RxJava & RxAndroid:
	- RxJava and RxAndroid libraries allow us to easily do async processing using principles of functional reactive programming. 
	


jUnitVersion 
	- used to implement unit test for app.
	


espressoCore:
	- used for UI test.

lifecycle :
	- used for viewModel libraries
	
koin :
	- used for dependency injection and create modules