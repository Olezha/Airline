# Airline [![Build Status](https://travis-ci.org/Olezha/Airline.svg?branch=master)](https://travis-ci.org/Olezha/Airline)

## Instructions for launching
```sh
git clone git@github.com:Olezha/Airline.git
cd Airline
mvn install
java -jar target/airline-1.0-SNAPSHOT.jar
```

## Usage 
Enter ```?list``` to list all commands
```sh
Airline
> ?list
abbrev	name	params
aa	add-aircraft	(type [WideBodyAirliner|Commuterliner|Helicopter], seating capacity, carrying capacity (kg), flight range (km), fuel consumption (liters per hour))
saa	show-all-aircraft	()
cc	carrying-capacity	()
asbfr	aircraft-sorted-by-flight-range	(direction [ASC|DESC])
asbfr	aircraft-sorted-by-flight-range	()
actagrofcp	airplanes-corresponding-to-a-given-range-of-fuel-consumption-parameters	(from (liters per hour), to (liters per hour))
tc	total-capacity	()
> |
```

Here's the sample session:
```sh
Airline
> saa
> aa Helicopter 18 5000 550 720
> saa
Helicopter(super=Aircraft(id=1, seatingCapacity=18, carryingCapacityKg=5000, flightRangeKm=550, fuelConsumptionLitersPerHour=720))
> asbfr
Helicopter(super=Aircraft(id=1, seatingCapacity=18, carryingCapacityKg=5000, flightRangeKm=550, fuelConsumptionLitersPerHour=720))
> tc
18
> cc
5000
> aa WideBodyAirliner 757 125000 15200 19400 
> saa
Helicopter(super=Aircraft(id=1, seatingCapacity=18, carryingCapacityKg=5000, flightRangeKm=550, fuelConsumptionLitersPerHour=720))
WideBodyAirliner(super=Airplane(super=Aircraft(id=2, seatingCapacity=757, carryingCapacityKg=125000, flightRangeKm=15200, fuelConsumptionLitersPerHour=19400)))
> asbfr
Helicopter(super=Aircraft(id=1, seatingCapacity=18, carryingCapacityKg=5000, flightRangeKm=550, fuelConsumptionLitersPerHour=720))
WideBodyAirliner(super=Airplane(super=Aircraft(id=2, seatingCapacity=757, carryingCapacityKg=125000, flightRangeKm=15200, fuelConsumptionLitersPerHour=19400)))
> asbfr desc
WideBodyAirliner(super=Airplane(super=Aircraft(id=2, seatingCapacity=757, carryingCapacityKg=125000, flightRangeKm=15200, fuelConsumptionLitersPerHour=19400)))
Helicopter(super=Aircraft(id=1, seatingCapacity=18, carryingCapacityKg=5000, flightRangeKm=550, fuelConsumptionLitersPerHour=720))
> actagrofcp 500 1000
Helicopter(super=Aircraft(id=1, seatingCapacity=18, carryingCapacityKg=5000, flightRangeKm=550, fuelConsumptionLitersPerHour=720))
> actagrofcp 1000 20000
WideBodyAirliner(super=Airplane(super=Aircraft(id=2, seatingCapacity=757, carryingCapacityKg=125000, flightRangeKm=15200, fuelConsumptionLitersPerHour=19400)))
> tc
775
> cc
130000
> exit

Process finished with exit code 0
```
