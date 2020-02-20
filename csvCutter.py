import glob

fileNames = glob.glob("unprocessedData/*.csv")

for name in fileNames:
    csv = open(name, "r")
    lines = csv.readlines()
    headers = lines[:3]

    part = 1
    date = ""
    
    for i, line in enumerate(headers):
        for j, c in enumerate(line):
            if c == ';':
                line = line[:j] + ',' + line[j+1:]

        headers[i] = line

    for line in lines[3:]:
        for i, c in enumerate(line):
            if c == ';':
                line = line[:i] + ',' + line[i+1:]
        
        if date != line[:10]:
            csvOut = open(name[2:-4] + " part " + str(part) + ".csv", "w")
            for header in headers:
                csvOut.write(header)

            date = line[:10]
            part += 1

        csvOut.write(line)
