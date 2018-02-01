# Airline
## company that stores airplanes [![Build Status](https://travis-ci.org/Olezha/Airline.svg?branch=master)](https://travis-ci.org/Olezha/Airline) [![codecov](https://codecov.io/gh/Olezha/Airline/branch/master/graph/badge.svg)](https://codecov.io/gh/Olezha/Airline) [![Dependency Status](https://www.versioneye.com/user/projects/5a72e8a80fb24f5752c0f584/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5a72e8a80fb24f5752c0f584)
Console application

### Instructions for launching
After cloning the repository,
run the following commands on the root folder of the repository

Optional: to switch to an `entry task stage` run ```git checkout c89cc42```
```sh
mvn clean install
java -jar target/airline-1.0.jar
```

### Usage 
Enter ```help``` to list all commands
```sh
Airline (to display available commands type help)
airline>help
AVAILABLE COMMANDS

Airline Shell Controller
        add: Add aircraft
        cc: Carrying capacity of all the aircraft in the airline
        fuel: Airplanes corresponding to a given range of fuel consumption parameters
        mock: Simulate objects
        show: Show all aircraft
        sort: List of aircraft of the company sorted by flight range
        tc: Total capacity of all the aircraft in the airline

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.


airline>|
```

Here's the sample session:
```sh
Airline (to display available commands type help)
airline>show
airline>mock
airline>show
Commuterliner(super=Airplane(super=Aircraft(id=1, seatingCapacity=10, carryingCapacityKg=2000, flightRangeKm=10000, fuelConsumptionLitersPerHour=150)))
Helicopter(super=Aircraft(id=2, seatingCapacity=18, carryingCapacityKg=800, flightRangeKm=3050, fuelConsumptionLitersPerHour=350))
WideBodyAirliner(super=Airplane(super=Aircraft(id=3, seatingCapacity=200, carryingCapacityKg=18000, flightRangeKm=14300, fuelConsumptionLitersPerHour=555)))
Commuterliner(super=Airplane(super=Aircraft(id=4, seatingCapacity=150, carryingCapacityKg=5550, flightRangeKm=1000, fuelConsumptionLitersPerHour=990)))
Helicopter(super=Aircraft(id=5, seatingCapacity=4, carryingCapacityKg=2110, flightRangeKm=2300, fuelConsumptionLitersPerHour=120))
WideBodyAirliner(super=Airplane(super=Aircraft(id=6, seatingCapacity=777, carryingCapacityKg=400, flightRangeKm=19500, fuelConsumptionLitersPerHour=1150)))
Commuterliner(super=Airplane(super=Aircraft(id=7, seatingCapacity=101, carryingCapacityKg=2014, flightRangeKm=1675, fuelConsumptionLitersPerHour=350)))
Helicopter(super=Aircraft(id=8, seatingCapacity=9, carryingCapacityKg=900, flightRangeKm=1900, fuelConsumptionLitersPerHour=120))
WideBodyAirliner(super=Airplane(super=Aircraft(id=9, seatingCapacity=1020, carryingCapacityKg=12000, flightRangeKm=10100, fuelConsumptionLitersPerHour=1600)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=10, seatingCapacity=555, carryingCapacityKg=14500, flightRangeKm=9900, fuelConsumptionLitersPerHour=2500)))
Commuterliner(super=Airplane(super=Aircraft(id=11, seatingCapacity=19, carryingCapacityKg=4500, flightRangeKm=5675, fuelConsumptionLitersPerHour=1001)))
Helicopter(super=Aircraft(id=12, seatingCapacity=24, carryingCapacityKg=2600, flightRangeKm=900, fuelConsumptionLitersPerHour=630))
WideBodyAirliner(super=Airplane(super=Aircraft(id=13, seatingCapacity=8, carryingCapacityKg=8950, flightRangeKm=12500, fuelConsumptionLitersPerHour=1340)))
Commuterliner(super=Airplane(super=Aircraft(id=14, seatingCapacity=189, carryingCapacityKg=1600, flightRangeKm=19000, fuelConsumptionLitersPerHour=2250)))
airline>
airline>tc
3084
airline>
airline>cc
75924
airline>
airline>fuel 800 1400
WideBodyAirliner(super=Airplane(super=Aircraft(id=6, seatingCapacity=777, carryingCapacityKg=400, flightRangeKm=19500, fuelConsumptionLitersPerHour=1150)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=13, seatingCapacity=8, carryingCapacityKg=8950, flightRangeKm=12500, fuelConsumptionLitersPerHour=1340)))
Commuterliner(super=Airplane(super=Aircraft(id=4, seatingCapacity=150, carryingCapacityKg=5550, flightRangeKm=1000, fuelConsumptionLitersPerHour=990)))
Commuterliner(super=Airplane(super=Aircraft(id=11, seatingCapacity=19, carryingCapacityKg=4500, flightRangeKm=5675, fuelConsumptionLitersPerHour=1001)))
airline>
airline>sort
Helicopter(super=Aircraft(id=12, seatingCapacity=24, carryingCapacityKg=2600, flightRangeKm=900, fuelConsumptionLitersPerHour=630))
Commuterliner(super=Airplane(super=Aircraft(id=4, seatingCapacity=150, carryingCapacityKg=5550, flightRangeKm=1000, fuelConsumptionLitersPerHour=990)))
Commuterliner(super=Airplane(super=Aircraft(id=7, seatingCapacity=101, carryingCapacityKg=2014, flightRangeKm=1675, fuelConsumptionLitersPerHour=350)))
Helicopter(super=Aircraft(id=8, seatingCapacity=9, carryingCapacityKg=900, flightRangeKm=1900, fuelConsumptionLitersPerHour=120))
Helicopter(super=Aircraft(id=5, seatingCapacity=4, carryingCapacityKg=2110, flightRangeKm=2300, fuelConsumptionLitersPerHour=120))
Helicopter(super=Aircraft(id=2, seatingCapacity=18, carryingCapacityKg=800, flightRangeKm=3050, fuelConsumptionLitersPerHour=350))
Commuterliner(super=Airplane(super=Aircraft(id=11, seatingCapacity=19, carryingCapacityKg=4500, flightRangeKm=5675, fuelConsumptionLitersPerHour=1001)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=10, seatingCapacity=555, carryingCapacityKg=14500, flightRangeKm=9900, fuelConsumptionLitersPerHour=2500)))
Commuterliner(super=Airplane(super=Aircraft(id=1, seatingCapacity=10, carryingCapacityKg=2000, flightRangeKm=10000, fuelConsumptionLitersPerHour=150)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=9, seatingCapacity=1020, carryingCapacityKg=12000, flightRangeKm=10100, fuelConsumptionLitersPerHour=1600)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=13, seatingCapacity=8, carryingCapacityKg=8950, flightRangeKm=12500, fuelConsumptionLitersPerHour=1340)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=3, seatingCapacity=200, carryingCapacityKg=18000, flightRangeKm=14300, fuelConsumptionLitersPerHour=555)))
Commuterliner(super=Airplane(super=Aircraft(id=14, seatingCapacity=189, carryingCapacityKg=1600, flightRangeKm=19000, fuelConsumptionLitersPerHour=2250)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=6, seatingCapacity=777, carryingCapacityKg=400, flightRangeKm=19500, fuelConsumptionLitersPerHour=1150)))
airline>
airline>sort -desc
WideBodyAirliner(super=Airplane(super=Aircraft(id=6, seatingCapacity=777, carryingCapacityKg=400, flightRangeKm=19500, fuelConsumptionLitersPerHour=1150)))
Commuterliner(super=Airplane(super=Aircraft(id=14, seatingCapacity=189, carryingCapacityKg=1600, flightRangeKm=19000, fuelConsumptionLitersPerHour=2250)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=3, seatingCapacity=200, carryingCapacityKg=18000, flightRangeKm=14300, fuelConsumptionLitersPerHour=555)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=13, seatingCapacity=8, carryingCapacityKg=8950, flightRangeKm=12500, fuelConsumptionLitersPerHour=1340)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=9, seatingCapacity=1020, carryingCapacityKg=12000, flightRangeKm=10100, fuelConsumptionLitersPerHour=1600)))
Commuterliner(super=Airplane(super=Aircraft(id=1, seatingCapacity=10, carryingCapacityKg=2000, flightRangeKm=10000, fuelConsumptionLitersPerHour=150)))
WideBodyAirliner(super=Airplane(super=Aircraft(id=10, seatingCapacity=555, carryingCapacityKg=14500, flightRangeKm=9900, fuelConsumptionLitersPerHour=2500)))
Commuterliner(super=Airplane(super=Aircraft(id=11, seatingCapacity=19, carryingCapacityKg=4500, flightRangeKm=5675, fuelConsumptionLitersPerHour=1001)))
Helicopter(super=Aircraft(id=2, seatingCapacity=18, carryingCapacityKg=800, flightRangeKm=3050, fuelConsumptionLitersPerHour=350))
Helicopter(super=Aircraft(id=5, seatingCapacity=4, carryingCapacityKg=2110, flightRangeKm=2300, fuelConsumptionLitersPerHour=120))
Helicopter(super=Aircraft(id=8, seatingCapacity=9, carryingCapacityKg=900, flightRangeKm=1900, fuelConsumptionLitersPerHour=120))
Commuterliner(super=Airplane(super=Aircraft(id=7, seatingCapacity=101, carryingCapacityKg=2014, flightRangeKm=1675, fuelConsumptionLitersPerHour=350)))
Commuterliner(super=Airplane(super=Aircraft(id=4, seatingCapacity=150, carryingCapacityKg=5550, flightRangeKm=1000, fuelConsumptionLitersPerHour=990)))
Helicopter(super=Aircraft(id=12, seatingCapacity=24, carryingCapacityKg=2600, flightRangeKm=900, fuelConsumptionLitersPerHour=630))
airline>
airline>add HELICOPTER 1 2 3 4
airline>
airline>fuel 0 10
Helicopter(super=Aircraft(id=15, seatingCapacity=1, carryingCapacityKg=2, flightRangeKm=3, fuelConsumptionLitersPerHour=4))
airline>
airline>help add


NAME
	add - Add aircraft

SYNOPSYS
	add [-type] string  [[-seating-capacity] int]  [[-carrying-capacity-kg] int]  [[-flight-range-km] int]  [[-fuel-consumption-liters-per-hour] int]  

OPTIONS
	-type  string
		Type [WideBodyAirliner|Commuterliner|Helicopter]
		[Mandatory]

	-seating-capacity  int
		Seating capacity
		[Optional, default = 0]

	-carrying-capacity-kg  int
		Carrying capacity (kg)
		[Optional, default = 0]

	-flight-range-km  int
		Flight range (km)
		[Optional, default = 0]

	-fuel-consumption-liters-per-hour  int
		Fuel consumption (liters per hour)
		[Optional, default = 0]



airline>
airline>add HELICOPTER -seating-capacity 8
airline>
airline>fuel 0 10
Helicopter(super=Aircraft(id=15, seatingCapacity=1, carryingCapacityKg=2, flightRangeKm=3, fuelConsumptionLitersPerHour=4))
Helicopter(super=Aircraft(id=16, seatingCapacity=8, carryingCapacityKg=0, flightRangeKm=0, fuelConsumptionLitersPerHour=0))
airline>
airline>exit

Process finished with exit code 0

```
