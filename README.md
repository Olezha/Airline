# Airline - company that stores airplanes [![Build Status](https://travis-ci.org/Olezha/Airline.svg?branch=master)](https://travis-ci.org/Olezha/Airline)
Console application (education)

## Instructions for launching
After cloning the repository,
run the following commands on the root folder of the repository
```sh
mvn clean install
java -jar target/airline-1.0.jar
```

## Usage 
Enter ```?list``` to list all commands
```sh
Airline (usage: type ?l for list all commands)
airline > ?list
abbrev	name	params
aa	add-aircraft	(Type [WideBodyAirliner|Commuterliner|Helicopter], Seating capacity, Carrying capacity (kg), Flight range (km), Fuel consumption (liters per hour))
saa	show-all-aircraft	()
cc	carrying-capacity	()
asbfr	aircraft-sorted-by-flight-range	(Direction [ASC|DESC])
asbfr	aircraft-sorted-by-flight-range	()
actagrofcp	airplanes-corresponding-to-a-given-range-of-fuel-consumption-parameters	(From (liters per hour), To (liters per hour))
m	mock	()
tc	total-capacity	()
airline > |
```

Here's the sample session:
```sh
Airline (usage: type ?l for list all commands)
airline > m
airline > saa
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
airline > tc
3084
airline > cc
75924
airline > asbfr
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
airline > actagrofcp 800 1200
WideBodyAirliner(super=Airplane(super=Aircraft(id=6, seatingCapacity=777, carryingCapacityKg=400, flightRangeKm=19500, fuelConsumptionLitersPerHour=1150)))
Commuterliner(super=Airplane(super=Aircraft(id=4, seatingCapacity=150, carryingCapacityKg=5550, flightRangeKm=1000, fuelConsumptionLitersPerHour=990)))
Commuterliner(super=Airplane(super=Aircraft(id=11, seatingCapacity=19, carryingCapacityKg=4500, flightRangeKm=5675, fuelConsumptionLitersPerHour=1001)))
airline > exit

Process finished with exit code 0
```
