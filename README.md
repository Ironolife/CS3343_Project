# CS3343_Project

Event Management System

-------------------------------------------------------

Classes:

Vendor
- Create New Event

User
- HKID (Unique)
- Guest
- Member
	- Tiers

Event
- StartTime, EndTime
- Location
- isMature
- getAverageRating(): double
- Reviews: ArrayList<Review>
	- reviewer: User
	- rating: double
	- comment: String
	- date: Date

Location
- Name (Unique)
- Capacity
* 2 Events should not have be at the same location with overlapping time.
	
Ticket
	- EntryTime, ExitTime, PurchaseTime
	- VIPTicket

Payment
- Credit Card
- Online Payment

Coupon
- Amount Deduction (Eg. Deduct $20)
- Percentage Deduction (Eg. 30% Off)

LogsRecorder
- Read and Write Logs
- Serialize Logs
	Log
	- Date
	- Message

EMS (Base Class with main())
	- Account Management
		- Registration
		- Login
			- FrontEnd
				- View Events, Tickets Details
				- UserFrontEnd
					- Ticket Purchase
					- Write Reviews
					- Upgrade User Tier
				- VendorFrontEnd
					- Create Event
					- Generate Tickets
					- Generate Coupons
					- View Sales
	- BackEnd
		- Store Vendor, User and Location Lists
		- Serialize Data

-------------------------------------------------------

General Practice:

- User UUID for Class ids to avoid duplication
- User ArrayList<T>
- Pull origin before working session
- Push to origin after working session
