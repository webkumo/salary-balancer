# salary-balancer - exercise 106

### Functionality

An application that gets file with employees and
verifies that:
* No one manager gets salary > 150% of average
of it's subordinates
* No one manager gets salary < 120% of average
of it's subordinates
* The company structure is flat, i.e.
between any employee and CEO there is not more than 5
managers

If there any violations - they will be printed in 
system.out stream.

### Run

To run application you may indicate filename as the
first parameter. If you wouldn't the default
`company.csv` will be used.

### File format

File format - csv, delimiter - ,
File header:
```
Id,firstName,lastName,salary,managerId
```
For CEO the field managerId is empty
example:
```
Id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,33000,124
305,Brett,Hardleaf,24000,300
```

### Possible problems
If there is any problem during parsing file you will
get an error code and additional information in
System.err stream:
* Error code 1 if file wasn't found 
* Error code 2 if found several employees with the
same id
* Error code 3 if found employees that impossible
to add to tree because there is no such an employee
with the id that is set for them as parent
* Error code 4 if found more than one CEO
* Error code 5 if there is no CEO found in file
* Error code 6 if different errors about format