# Assignment
## Scenarios: 

1. Hiking
* Rent a ReachNow to drive to mountain rainier on weekends with friends
* On the way, fill the gas
* On the way, eat at restaurant for lunch
* Buy the ticket to get into the park
* Book a hotel or check in the hotel.

2. Organise a career fair (Suppose you are the organiser)
3. Order Pizza from Pizza Hut
4. Design a code sharing platform (eg: Github).
5. Design a soft-drink/snacks vending machine.

## Tips : 
1. You may go as deep as you want , like the first scenario will be an example, you can simplify it or make it more complex.
2. Use pseudo code. This assignment has nothing to do with the real code, we want you to experience what is object oriented language. 


## Success criteria:
1. Be able to parsing the daily activities into some small block and learn the way to design things. Different may have different ways to parse the same scenario. So make your own copy.   
2. Be able to make progress according to reviews from teachers and TA. 

## Requirement:

1. Deadline is Sep, 15th. If you encounter any problem to meet this deadline, please contact TA or Professor directly.  
2. Individual work, not team project. But can have discuss with other classmates.

---
/*
GOOD WORK
SCORE: 10
*/
# Accomplishment

## 1. Hiking
### 1.1. Rent a ReachNow to drive to mountain rainier on weekends with friends

#### Objects

**User**  
*Data*: name, phone number, email address, home address, credit card, real time location, place of departure, place of arrival, phone, application user name, password, car  
*Behaviors*: register, searchInternet, enterPhoneNumber, openReachNow, closeReachNow, findCar, holdCar, unlockCar, lockCar, driveCar, stopCar, parkCar  

**CreditCard**  
*Data*: bank, card number, CCV, expiration date, holder's name  
>Note: There is no behavior for credit cards so I just don't list it. So does the below objects  

**CreditCardCompany**  
*Behavior*: authorizeTransaction  

**Car**  
*Data*: model, color, parking location, status, 

**Location**  
*Data*: latitude, altitude, name  

**Phone**  
*Data*: type (iOS | Android), internet connection  
*Behavior*: sendMessage, receiveMessage, download, upload,  

**Internet**  
*Data*: ReachNow website, .... : Group of Websites (Collection of Websites)  
*Behaviors*: searchForWebsites, getGPSLocation  

**DriveLicence**  
*Data*: licence#, expiration date, holder  

#### Sequence  
**One-time Behavior**  
//new users have to download and install the ReachNow application in advance  

User carUser;  
Phone iPhone;  
CreditCard card;  
Internet internet;  

```
//a user opens the ReachNow website, enter the cell phone number and then download the and register his/her information
bin.searchInternet -> phone, internet: ReachNow website (https://reachnow.com/)  

//user bin gets the download link on his mobile phone after entering the phone number in the website  
if(bin.phoneNumber is not valid)
     "bin cannot use this service. Abort!"
else
    bin.enterPhoneNumber -> internet, phone: phone.receiveMessage (ReachNow app. download link)
    
    if(iPhone.internetConnect is null)
    "cannot continue. abort!"
    else
        iPhone.download -> internet, phone: ReachNow application installation

//bin users his name, email, phone number, credit card and drive licence information to register
bin.register -> name, phone number, credit card, driver licence: validated ReachNow user account (user name, password)
```

**Car Usage**

```
//bin opens the ReachNow application and find a desired car
if(iPhone.internet connection is fine)
    bin.openApplication -> internet, real time location: collection of cars around

    //then he reserve this car (hold)
    if(can.status = available)
        bin.holdCar -> internet, car: car.status = reserved

    //once he get the car's parking place, unlock this car
    bin.unlockCar -> car, phone: car.unlocked
end if

//bin drives this car
bin.startCar -> car: car.status = started
if(car.gas = 0)
    "Out of gas. Please add some gas before driving."
else
    bin.driveCar -> car: car.running

```
### 1.2. On the way, fill the gas

#### Objects
**GasStation**  
*Data*: gases[], location  

**Gas**  
*Data*: gas#, price  

**User**  
+*Data*: 

+*Behavior*: findGasStation, fillGas  
>Note: The '+' means more data or behavior adding on the existing objects.

#### Sequence
```
GasStation station;

bin.findGasStation -> gas stations[]: desired gas station
bin.stopCar -> car: car.status = stopped
bin.fillGas -> car, gas: car.gas = 100 (assume add 100 gas)
bin.startCar -> car: car.status = running
```

### 1.3. On the way, eat at restaurant for lunch

#### Objects

**Restaurant**
*Data*: dish[], location, owner  
*Behavior*: takeOrder, serveDish, chargeBill

**Order**  
*Data*: dish[], date, customer, money to charge

**Dish**  
*Data*: flavor, ingredients, price

**User**  
+*Behavior*: orderDish, eat, payBill

#### Sequence

```
Restaurant restarant;
Order order;
Dish yummyFood[];

bin.findRestaurant -> collections of restaurant[]: restarant
bin.stopCar -> car: car.status = stopped
bin.orderDish -> restarant, collection of dishes: order
restarant.takeOrder -> order: yummyFood
restarant.serveDish -> yummyFood: yummy food are served
bin.eat -> yummyFood
restarant.chargeBill -> order: some money to pay
bin.payBill -> order: money is pay and bin can leave
bin.startCar -> car: car.status = started
bin.driverCar -> car: car.status = running
```
### 1.4. Buy the ticket to get into the park

#### Objects

**Park**  
*Data*: name, location, 

**Ticket**  
*Data*: price, type, valid date

**User**  
+*Behavior*: buyTicket, enterPark, tour, leavePark

#### Sequence
```
bin.stopCar;
bin.parkCar;
bin.lockCar;
bin.buyTicket -> user, credit card: ticket
if(ticket is valide)
    bin.enterPark;
    bin.tour : tour this mountain
    bin.leavePark;
else
    "Your ticket is not valid. Please buy a valid one."

bin.startCar -> car: car.status = started;
bin.driveCar -> car: car.status = running;
```

### 1.5. Book a hotel or check in the hotel

#### Objects

**Hotel**  
*Data*: location, rooms[],

**Room**  
*Data*: room number, price, type, status, dueDate

**User**  
*Data*: gender  
*Behavior*: bookRoom, checkin, checkout, live, cancelRoom

#### Sequence

```
Hotel hotel;
Room rooms[];

bin.stopCar;
bin.ParkCar;
bin.lockCar;
bin.releaseCar; // no use any longer.
bin.bookRoom -> hotel, credit card: rooms
bin.checkin;
loop (rooms.dueDate-- >0)
    bin.live;
end loop

bin.checkout -> credit, room[]: rooms.dueDate = 0;
```

## 2. Organise a career fair(Suppose you are the organiser)

### Book the time and place to hold this career fair.

#### Objects

**CareerFair**  
*Data*: startTime, endTime, address, name, organiser  

**People**  
*Data*: name, address, phone, genda,  

**Organiser** extends **People**  
+*Behavior*: bookRoom()  

**Room**  
*Data*: address, volume, schedule[], administrator   

**Schedule**  
*Data*: startTime, endTime, requester, availablity  

#### Sequence
```
People organiser = new Organiser(), admistrator = new People();
CareerFair fair;
Room room;
Schedule careerFairSchedule;

organiser.bookRoom() -> fair, room: fair.address = room.address, schedule.requester = organiser, schedule.startTime = fair.startTime, schedule.endTime = fair.endTime, room.schedule[] +=careerFairSchedule;
```

### Invite and confirm companies to attend this career fair.

#### Objects

**Company**  
*Data*: name, address, phone, employees[], description, status[invited|accepted|rejected|noresponse|checkedin]  
*Behavior*: recieveInvitation(), acceptInvitation(), rejectInvitation();  

**Organiser**  
+*Behaviror*: invite(), 

**CareerFair**  
+*Data*: attendCompanies[], attendStudents[];   

#### Sequence
```
Company companies[];

while(not all target companies are invited)
    organiser.invite() -> companies;
    if(company[i] is interested)
        company[i].acceptInvitation();
        company[i].status = accepted;
    else if ( company[i] is not interested at all)
        company[i].rejectInvitation();
        company[i].status = rejected;
    esle company[i].status = noresponse;
    end if
end while
```

### Invite students to join this career fair.

#### Objects

**Student** extends **People**  
+*Data*: id, college, GPA, grade, yearOfGraduation, skills[], resume  
+*Behavior*: getInvitation(), acceptInvitation(), rejectInvitation()  

#### Sequence
```
Sdudent students[];

while(not all students are invited) {
    organiser.invite() -> student;

    if(student is interested) {
        student.acceptInvitation();
        fair.students[] += student;
    } else {
       student.rejectInvitation();
    }
}

```

### On the career fair day, companies check in, and setup its own cabinet.

#### Objects

**Company**  
*Data*: cabinet#, checkinTime,  
*Behavor*: checkin(), setupCabinet()  

#### Sequence
```
when( a company i checks in)
    company[i].checkin() -> currentTime: company[i].status = checkedin; compnay[i].checkinTime = currentTime;
    company[i].setupCabinet() -> company[i] : company[i].cabinet# = some cabinet;
end when
```

### Students register in the front desk and check in.

#### Objects

**Student**  
+*Data*: status(invited, accepted, interviewed, offerred, noresponse), checkinTime  
+*Behavior*: checkin(),   

#### Sequence
```
student[i].checkin() -> status, currentTime: student[i].status = checkedin; student[i].checkinTime = currentTime;
 
``` 

>Note: Afater all is set up, the organiser hosts this career fair and introduce this fair briefly. bla bla bal...

### Studetns visit the cabinet and talk with the interviewers in a company

#### Objects

**Student**  
+*Data*:   
+*Behevior*: getInterviewed(),   

#### Sequence
```
students[i].getInterviewed() -> company: students[i].status = interviewd. 
```

### Interviewers talk and interview the students if they are interested

#### Objects

**Interview**  
*Data*: interviewer, interviewee, startTime, endTime, duration, grade  
*Behaviors*:

**Company**  
+*Data*: interviews[]  
+*Behavior*: startInterview(), endInterview(), gradeAnInterviewee(); sendOffer()  

#### Sequence
```
Interview interviews[];

if(companies[i] interested in students[j]) 
    companies[i].startInterview() -> students[j]: students[j].status = interviewed;
    companies[i].gradeAnInterviewee() -> interview: interview.grade

    //After interview, some students may get an offer right away, while some students need to wait for the result.

    if(interview.garde is good)
        companies[i].sendOffer -> students[j] : student[j].status = offered;
    end if
end if
```

### The organiser would like to setup a survey to collect the feedback of this career fair from the companies and students.

#### Objects

**Survey**  
*Data*: questions[], answers[], createdTime, responseTime, creater, responser  

**Organiser**  
+*Behavior*: createSurvey(), sendSurvey(), recievedSurvey()  

**Student**  
+*Behavior*: recieveSurvey(), answerQuestion(), submitSurvey()  

**Company**  
+*Behavior*: recieveSurvey(), answerSurvey(), submitSurvey()   

**CareerFair**  
+*Data*: survey


#### Sequence
```
Survey careerSurvey;

organiser.createSurvey() -> : careerSurvey;
organiser.sendSurvey() -> careerSurvey;

for( every student students[i])
    students[i].recieveSurvey() -> careerSurvey : (survey is recieved, and ready to be answered);
    students[i].answerSurvey() -> careerSurvey.questions[]: qunestions[] = some value;
    students[i].sumbitSurvey() -> careerSurvey : careerSurvey is submitted;
end for

Survey companySurvey;

while (every company companies[i])
    companies[i].recieveSurvey(): companySurvey;
    companies[i].answerSurvey() ->  companySurvey : companySurvey.answers[] = some values; 
    companies[i].submitSurvey() -> companySurvey;
end while
```
## 3. Order Pizza from Pizza Hut
This is for a customer order a pizza in a store rather than online.

#### Objects

**Customer**  
*Data*: name, myOrder  
*Behavior*: order(), pay()

**Order**  
*Data*: pizzas[], orderTime, money, orderedBy, createdBy

**Pizza**  
*Data*: namme, size, weight, crust, crustFlavor, souce, souceAmount, cheese, cheeseAmount, topping[]{bacon, beef, pork, onions, mushrooms}  

**Waiter**  
*Data*: name, age,  
*Behavior*: confirmOrder(), placeOrder(), printOrder(), chargeBill(), servePizza(),   

**Chef**  
*Data*: name, age  
*Behavior*: cook()  

 
#### Sequence
```
Pizza pizzaFromMenu[];
Customer bin;
Waiter james;
Chef li;

while (bin has more to order)
	// a customer named bin selects a desired pizza from a collection of pizza based on his flavor 
	bin.order() -> a collection of pizzas in the menu : bin.myOrder  += a new selected pizza
end while

// then he talks with the waiter james to oder, and james confirms his order
james.confirmOrder(): bin.order : order confirmation 

// james places the order in the system, and then print the order
james.placeOrder() -> bin.order : order was placed in the system
james.printOrder() -> bin.order : order.money = some money to pay
james.chargeBill() -> bin.order : 
if(bin has enouth money to pay)
	bin.pay() -> order.money: bill is payed
end if

// Chef li starts to cook this pizza when he is notifed by the order system
li.cook() -> ingredients, powder, bin.order: yummy pizzas will be ready

// waiter james then serves the pizzas in this order to bin
if( the ordered pizza is ready)
	jame.servePizza()-> bin.order: bin get the pizza and he is happy now.
end if

```

## 4. Design a code sharing platform (eg: Github).

To design this platform, mostly it is to design the objects and behaviors on these objects.

### Objects

**User**  
*Data*: firstName, lastName, uid, location, link, repositories[ ]  
*Behaviors*:  
* working area behaviors  
init(), clone()  
* current change behaviors  
add(), mv(), reset(), rm(), createCode(), edit(), save()   
* examine the history and state  
bisect(), grep(), log(), show(), status()  
* grow, mark and tweak common history  
branch(), checkout(), commit(), diff(), merge(), rebase(), tag()  
* collaborate  
fetch(), pull(), push()  
* repository operations
setPrivate(), setPublic(), delete(), archive(), deactivate()  

**Repository**  
*Data*: master, branches[ ], URL, httpURL, sshURL, origin  

**Branch**  
*Data*: sourcecodeFiles[], status,  

**SourceCode**  
*Data*: fileName, contents, hashcode  

There are many scenarios and use cases of this code sharing platform. I would just list a few and explain the operation sequence.

### work on it in the local working area 

```
User bin;
Repository info5100;
SourceCode assignments[];
Branch work;

bin.init(): info5100 repository is created
while(bin has more files to add)  
    bin.create() -> assignment: new source code file assignment is created;  
    bin.add() -> assignments[ ], info5100 : info5100.origin.sourcecodeFiles[ ] += assignment;   
end while
bin.push() -> assignments[ ] : remote upstream saves these source code files; 

//setup a new branch 'work', and work on it
bin.branch() -> info5100: working branch work is created
bin.checkout -> info5100, work : work.status = checkout
bin.edit() -> work : update the work.sourceCode files
bin.save() -> work: save it in local

bin.commit() -> assignments[ ]: saving in local working area
bin.merge() -> work, master: merge the changes in work to master

//Note: this process could be a loop for creating & sharing multiple repositories.
 
```

### collaborate

```
bin.setPublic() -> info5100: info5100.status = public; // now, infor5100 is visible to others by its URL

User jane;

//a new user jane can work on this repository by forking it
jane.fork() -> info5100: jane.repository += info5100;

//jane does some changes on her info5100
jane.edit() -> jane.info5100: some source code files are updated
jame.add(); jane.commit(); : remote jane.info is updated
jame.pull() -> jane.info5100: request to merge jane.info5100 with bin.info5100

if(no merge conflict)
	bin.merge() -> jane.info5100, bin.info5100: merge
else
	"Error: this could not be done!"
end if
```

## 5. Design a soft-drink/snacks vending machine.
 
### Objects  
**VendingMachine**  
*Data*: location, color, weight, goods[ ], status
*Behaviors*: sell(), charge(),  abort(),  

**Supplier**  
*Data*: goods[ ]
*Behaviors*: fill(), fix(), 
 
**Goods**  
*Data*: name, price, capacity, currentNumber, buyNumber, thresholdNumber,  
**Behaviors*: 

**Consumer**  
*Data*: selectedGoods[ ], money
*Behaviors* : selectGoods(), confirmSelections(), pay(), cancelSelection()

### Sequence  
```
VendingMachine vm;
Consumer bin;
Goods goods[];
Goods desiredGoods;

//if the machine is broken, then exit.
if(vm.status = out of order)
	return "You cannot buy anything."
	exit;
end if

while(bin doesn't finish select)
	if(desiredGoods.currentNumber > 0)
	bin.selectGoods()-> goods[] : selectedGoods[] += desiredGoods;
	
	if(vm.abort()) break;
end while

bin.confirmSelections() -> bin.selectedGoods[]: transaction start;
vm.charge() -> bin.selectedGoods[]: transaction pay;
if(bin.money is enough to pay)
	bin.pay() ->  bin.selectedGoods[]: transaction confirmation;
end if

//then vm start to sell and deduce the current number of each goods in the selectedGoods[];
vm.sell() -> bin.selectedGoods[]: 
for(i=0;i<bin.selectedGoods[],length.length;i++) {
	while(bin.selectedGoods[i].buyNumber-- > 0) {
		vm.sell() -> bin.selectedGoods[i]: bin.selectedGoods[i].currentNumber--;
	}
}

//regular check the currentNumber of each goods in the machine and refill if it needs
Supplier sup;

foreach(goods[i] in the machine)
	if(goods[i].currentNumber < goods[i].thresholdNumber)
		sup.refill()-> goods[i]: goods[i].currentNumber = goods[i].capacity;
	end if
end foreach
```
