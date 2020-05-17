import glob

fileNames = glob.glob("unprocessedData/*.csv")

for name in fileNames:
    csv = open(name, "r")
    lines = csv.readlines()
    headers = lines[:3]

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
            date = line[:10]
            csvOut = open("d" + name[12:-4] + "_" + str(date) + ".csv", "w")
            for header in headers:
                csvOut.write(header)

        csvOut.write(line)