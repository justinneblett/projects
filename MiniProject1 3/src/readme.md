High's Hardware and Gardening Store Management System

A comprehensive Java-based store management system for High's Hardware and Gardening, featuring inventory management, staff scheduling, and various store operations

Features
    
Core Store Operations

Inventory Management: Add, find, sell items with quantity tracking

Staff Management: Hire, fire, promote employees

Cost Tracking: Check item prices and availability

Schedule Creation: Generate staff schedules based on availability

Plank Cutting: Prime-number based plank cutting algorithm

Supported Commands: ADD (add new item to inventory), COST (get item price), FIND (find item location and quantity), SELL (sell items from inventory), QUANTITY (check item quantity), HIRE (hire employee), FIRE (fire employee), PROMOTE (promote employee), SCHEDULE (create staff schedule), SAW (plank cutting operation), EXIT (exit application)

Staff Roles

Manager(M): Store management Responsibilities, Cashier(C): Customer service and transactions, Gardening Expert(G): Specialized Gardening Knowledge

Input Files: input.txt, inventory.txt, staff_availability_IN.txt, shift_schedules_IN.txt

Output Files: output.txt, staff.txt, store_schedule_OUT.txt

Inventory File Format

'Item Name',price,quantity,aisle
'Hammer',30,2,3
'Multi Bit Ratcheting',59,1,4

Staff Availability Format:

FirstName LastName age role days_available
Jason Henderson 35 M M.T.TR.SAT.SUN
Catherine Garcia 19 C M.W.F.SAT

Shift Schedules Format:

DAY START_TIME END_TIME
M 0800 1700
SAT 0900 2130

Usage:

javac edu/iu/c212/utils/StoreMain.java
java edu.iu.c212.utils.StoreMain


Sample Input:

ADD 'Cordless Screwdriver' 30 1 2
COST 'Multi Bit Ratcheting'
FIND 'Hammer'
SELL 'Hammer' 1
HIRE 'Lewis Hamilton' 35 G
PROMOTE 'Lewis Hamilton' M
SCHEDULE
SAW
EXIT

Sample Output:

Cordless Screwdriver was added to inventory
Multi Bit Ratcheting: $59
2 Hammer are available in aisle 3
1 Hammer was sold
Lewis Hamilton has been hired as a Gardening Expert
Lewis Hamilton was promoted to Manager
Schedule created.
Planks sawed.
Thank you for visiting High's Hardware and Gardening!


Store.Java: Main business logic handler that processes commands from input file and manages store operations

FileUtils.java: Handles all file I/O operations such as reading inventory and staff data, writing updated information back to files, and command processing and output generation

StaffScheduler.java: Advanced scheduling system that reads staff availability, processes shift schedules, generates optimized staff assignments, and creates timestamped schedule outputs

SawPrimePlanks.java: the algorithm for cutting planks into prime-length segments using recursive division

Java 8 or Higher, Standard Java I/O libraries

Installation

Clone or download project files, ensure Java 8+ installed, compile files: javac edu/iu/c212/utils/*.java edu/iu/c212/models/*.java edu/iu/c212/programs/*.java

Place input files into resource directory and run application: java edu.iu.c212.utils.StoreMain


Known Issues & Troubleshooting

Multi-word commands like FIND 'Hand Truck' may not parse correctly due to inconsistent quote handling

May have issues with multi-word item names (eg.,COST 'Multi Bit Ratcheting' vs Multi Bit Ratcheting)

ADD command expects exactly 6 parts but handles multi-word names inconsistently

File I/O Problems

writeStaffToFile() has a logic error - the PrintWriter is inside the loop and overwrites instead of appends. Same with writeStoreScheduleToFile()

Items names in inventory.txt include quotes that may cuase parsing issues

Data Consistency Issues:

Inconsistent handling of quotes in item names between files and commands

Staff roles use abbreviations (M/C/G) in input but full names in processing

FIRE command removes from output but doesn't actually remove from the staff list

Inconsistent format compared to expected output


Recommendation for Fixes:

Standardize quote handling across all commands

Fix file writing methods to properly remove data

Implement actual staff removal in FIRE command

Standardize output formatting to match expected results

Add input validation for all commands

Contributing

This projects  follows standard Java conventions and includes comprehensive file I/O handling for store management operations.

License:

Educational project for IU C212 course.
