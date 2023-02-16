using System;
using System.Collections;
using System.IO;
using UnityEngine;
using Unity.Networking;
using UnityEngine.UI;

/*
 * Example for downloading a single file.
 */
public class SingleFileDownload : MonoBehaviour
{
    public Button btn;
    public Text text;
    public InputField inputURL;

    private BackgroundDownload _download;

    private void Awake()
    {
        btn.onClick.AddListener(DoStart);

    }

    public void Log(string str)
    {
        text.text = $"{text.text}\n[{DateTime.Now.ToString("HH:mm:ss")}] {str}";
        Debug.Log($"[{DateTime.Now.ToString("HH:mm:ss")}] {str}");
    }

    public void DoStart()
    {

        try
        {
            Log($"开始下载[{inputURL.text}]");
            string fileName = Path.GetFileName(inputURL.text);
            string destinationFile = Path.Combine(Application.persistentDataPath, fileName);

            if (File.Exists(destinationFile))
            {
                Log("File already downloaded");
                return;
            }

            var downloads = BackgroundDownload.backgroundDownloads;
            if (downloads.Length > 0)
                StartCoroutine(WaitForDownload(downloads[0]));
            else
            {
                Uri url = new Uri(inputURL.text);
                _download = BackgroundDownload.Start(url, fileName);
                StartCoroutine(WaitForDownload(_download));
            }
        }
        catch (Exception e)
        {
            Log($"[Error]{e}");
            _download = null;
        }
    }

    IEnumerator WaitForDownload(BackgroundDownload download)
    {
        yield return download;
        if (download.status == BackgroundDownloadStatus.Done)
            Log("File successfully downloaded");
        else
            Log("File download failed with error: " + download.error);
        _download = null;
    }

    public void Update()
    {
        if (_download != null && Time.frameCount % 20 == 0)
        {
            Log($"下载进度：【{_download.progress}】");
        }
    }
}
