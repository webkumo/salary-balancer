# salary-balancer - exercise 106

### Functionality

An application that gets file with employees and
verifies that:
* No one manager gets salary > 150% of average
of it's subordinates
* No one manager gets salary < 120% of average
of it's subordinates
* The company structure is flat, i.e. between any
employee and the CEO there is not more than 4
managers

If there are any violations - they will be printed in 
`System.out` stream.

### Run

To run application you may indicate filename as the
first parameter. If you wouldn't the default
`company.csv` will be used.

### File format
* File size - can be configured by environment variable
`max_lines`
* File format - csv, delimiter - ,
* File header:
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
get an error code (as it is the best way to make
scripts that use console app) and additional
information in `System.err` stream (to make answer
verbose for user):
* Error code 1 if file wasn't found 
* Error code 2 if found several employees with the
same id
* Error code 3 if found employees that impossible
to add to tree because there is no such an employee
with the id that is set for them as parent
* Error code 4 if found more than one CEO
* Error code 5 if there is no CEO found in file
* Error code 6 if different errors about format
* Error code 7 if file is too long
(default - 1000 employees)

### Assumptions applied for the task
* Input file name: it much better for console app to
be able to get it as a parameter, but also provided
default value
* Output on violations: made to `System.out` each on
new line, if user wants he can easily redirect it
to file with console instruments (`>` or `>>`)
* In case of problems during parsing: output to
`System.err` and provide error code - in case if
user wants to add this program in some script
chain it is much easier if he have error codes,
`System.err` was chosen to make difference with
normal application answer
* Parsing problems: I decided to consider as
source file problem next cases:
  * no file or file cannot be read
  * file doesn't have exact header
  * file has only the header (so it is nothing to analyze)
  * file doesn't contain the CEO
  * file contains more than one CEO
  * file has several employees with same id
  * file has orphans - any employees that has parent id for which there are no employee exists
  * file has non-numeric value in numeric field
  * file has entry with wrong number of fields
  * file is too long