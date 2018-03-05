
   
    //checks for the button click event
    $("#search").click(function(e){
          
            //get the form data and then serialize that
            dataString = $("#myAjaxRequestForm").serialize();
           
            //get the form data using another method 
            var query = $("input#searchq").val(); 
            dataString = "countryCode=" + query;
           
            //make the AJAX request, dataType is set to json
            //meaning we are expecting JSON data in response from the server
            $.ajax({
                type: "POST",
                url: "Testing",
                data: dataString,
                dataType: "json",
               
                //if received a response from the server
                success: function( data, textStatus, jqXHR) {
                    //our country code was correct so we have some information to display
                     if(data.success){
                         $("#ajaxResponse").html("");
                         $("#ajaxResponse").append("<b>date_time:</b> " + data.countryInfo.date_time + "<br/>");
                         $("#ajaxResponse").append("<b>description:</b> " + data.countryInfo.description + "<br/>");
                        
                     } 
                     //display error message
                     else {
                         $("#ajaxResponse").html("<div><b>Search Value in Invalid!</b></div>");
                     }
                },
               
                //If there was no resonse from the server
                error: function(jqXHR, textStatus, errorThrown){
                     console.log("Something really bad happened " + textStatus);
                      $("#ajaxResponse").html(jqXHR.responseText);
                },
               
                //capture the request before it was sent to server
                beforeSend: function(jqXHR, settings){
                    //adding some Dummy data to the request
                    settings.data += "&dummyData=whatever";
                    //disable the button until we get the response
                    $('#myButton').attr("disabled", true);
                },
               
                //this is called after the response or error functions are finsihed
                //so that we can take some action
                complete: function(jqXHR, textStatus){
                    //enable the button 
                    $('#myButton').attr("disabled", false);
                }
     
            });        
    });



$("#add").click(function(e){
	$("div#populate").append(
			// Creating Form Div and Adding <h2> and <p> Paragraph Tag in it.
			$("<h3/>").text("reservation"), $("<p/>").text("This is my form. Please fill it out. It's awesome!"), $("<form/>", {
			action: '#',
			method: '#'
			}).append(
			// Create <form> Tag and Appending in HTML Div form1.
			$("<input/>", {
			type: 'text',
			id: 'vname',
			name: 'name',
			placeholder: 'Date-Time(ex. May 11 8PM)'
			}), // Creating Input Element With Attribute.
			$("<input/>", {
			type: 'text',
			id: 'vemail',
			name: 'email',
			placeholder: 'Description'
			}), $("<input/>", {
			type: 'submit',
			id: 'submit',
			value: 'Submit'
			}),$("<input/>", {
				type: 'submit',
				id: 'cancel',
				value: 'cancel'
				})))
	
});