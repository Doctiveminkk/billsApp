# billsApp

This application's main goal is to manage house bills for people living toghether.
It reads file names in a given directory and copies to the system's clipboard the bills of a given month with total amount and split amount according to how many people the user has set.

Example of output:
Internet 30.0 €
Electricity 39.55 €
Water 45.77 €
Natural Gas 19.66 €
Total: 134.98 €
Divided by 4: 33.74 Euros

File names have to conform by the following name convention: "<year>-<month> <name>.<amount>".

Currently the user can set the number of people the amount is divided by (divisor), the ignored character for files that must be ignored by the program (these files's name must begin with the ignored character!!) and the directory of said files.

The default settings are:
Divisor = 3;
Ignored Character = ',';
Directory = ./;

I hoppe you enjoy and that it helps you in any way. I'm always open to suggestions and contributions :)

Stay Awesome,
Doctiveminkk
