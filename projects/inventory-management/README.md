# Inventory and Incentive Management Platform for Automotive Dealers and Car Shoppers

Build a platform for Automotive Dealers to manage theirInventory and Incentives. Develop a user interface for Car shoppers to shop forVehicles on different automotive dealership lots.

Platformshould be able to support multiple car dealers and each dealer should be ableto login to the system to manage their Inventory and Incentives. 

Car shoppers should have an option to select a dealer andbrowse their inventory and search for cars with different filter criterion.

# Terminology

Every Dealer will haveInventory. Inventory is a collection of Vehicles. Each Vehicle has data likeid, year, make, model, vin, price, color, photo, description, category(new/used), type (car/suv/van/wagon,truck) and other potential attributes. 

Incentiveis a potential cash discount on a vehicle like $500 discount for all Used carsor $250 cash discount for 2017, Honda Accord etc. Incentive can be applied toone vehicle, group of vehicles or vehicles matching a criterion. EveryIncentive created should be applied for the matching vehicles. If a Vehicle’sprice is $25000 and it has two incentives of ($250 and $500), then the saleprice of the vehicle will be $24225 ($25000 - $250 - $500).

Every Incentive will have a title, start date, end date, description, cash value andthe vehicle criteria for which the Incentive can be applied.

# Epics/High Level Requirements

·     User Interface for selecting a Dealer 

·     User interface for managing Inventory for aparticular dealer

·     User Interface for managing Incentives for aparticular dealer

·     User interface for consumers to browse Inventoryof a particular dealer

·     User interface for consumers to browse aparticular Car when they click a vehicle on the Inventory Listing page

# Key Domain Objects

·     Dealer

·     Inventory

·     Vehicle

·     Incentive

# Design and Source Code

This is a group project and my team is responsible for the inventory management, which is available from here https://github.com/iShiBin/inventory-management

All the other source codes and documents are under repository https://github.com/vykuntaharsha/NeuSep17-Project-Group1