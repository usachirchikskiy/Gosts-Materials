#!flask/bin/python
from controller import Parser as parser
from flask import Flask, jsonify, request,send_file

app = Flask(__name__)
apiHelper = parser.Parser()

@app.route('/gosts', methods=['GET'])
def get_gosts():
    return jsonify(apiHelper.gosts)

@app.route('/materials', methods=['GET'])
def get_materials():
    return jsonify(apiHelper.materials)

@app.route("/download",methods=['GET'])
def downloadFile():
    print(request.args)
    if request.args:
        url = request.args['url']
    return send_file(apiHelper.downloadFile(url), as_attachment=True)

@app.route('/searchMark', methods=['POST'])
def search_mark():
    print("Request",request)
    mark = request.json["mark"]
    return jsonify(apiHelper.searchMark(mark))

@app.route('/searchAdvanced', methods=['POST'])
def search_advanced():
    return jsonify(apiHelper.searchAdvanced(request.json))

if __name__ == '__main__':
    app.run(host="0.0.0.0",debug=True)
