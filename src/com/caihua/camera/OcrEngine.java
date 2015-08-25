package com.caihua.camera;

import java.io.UnsupportedEncodingException;

import android.graphics.Rect;
import android.os.Handler;

import com.ym.idcard.reg.NativeOcr;

public class OcrEngine {
	public static final int BIDC_ADDRESS = 0x6;
	public static final int BIDC_BIRTHDAY = 0x5;
	public static final int BIDC_CARDNO = 0x3;
	public static final int BIDC_FOLK = 0xb;
	public static final int BIDC_ISSUE_AUTHORITY = 0x7;
	public static final int BIDC_MEMO = 0x63;
	public static final int BIDC_NAME = 0x1;
	public static final int BIDC_NON = 0x0;
	public static final int BIDC_SEX = 0x4;
	public static final int BIDC_VALID_PERIOD = 0x9;
	public static final int OCR_LAN_CHINESE = 0x2;
	protected NativeOcr mOcr = null;
	protected long m_pCurField = 0L;
	protected long m_pEngine = 0L;
	protected long m_pField = 0L;
	protected long m_pImage = 0L;
	protected long[] m_ppEngine = null;
	protected long[] m_ppField = null;
	protected long[] m_ppImage = null;

	private int getFieldId() {
		return this.mOcr.getFieldId(this.m_pCurField);
	}

	private String getFieldText(int keyLanguage) {
		long field = this.m_pCurField;
		byte[] text = new byte[256];
		this.mOcr.getFieldText(field, text, 256);
		return StringManager.convertGbkToUnicode(text);
	}

	private boolean isFieldEnd() {
		return this.m_pCurField == 0L;
	}

	private void getNextField() {
		if (!isFieldEnd()) {
			m_pCurField = mOcr.getNextField(m_pCurField);
		}
	}

	public boolean startBCR(String cfgPath, String dataPath, int language,
			boolean recognAll) {
		boolean bool = false;
		if (this.mOcr.startBCR(this.m_ppEngine,
				StringManager.convertUnicodeToAscii(dataPath),
				StringManager.convertUnicodeToAscii(cfgPath), language) == 1) {
			this.m_pEngine = this.m_ppEngine[0];
			if (!recognAll)
				this.mOcr.SetSwitch(this.m_pEngine, 13, 1);
			bool = true;
		}
		return bool;
	}

	public void closeBCR() {
		if ((m_ppEngine != null) && (mOcr != null)) {
			mOcr.closeBCR(m_ppEngine);
			m_ppEngine = null;
			m_pEngine = 0;
		}
	}

	public long getYData(byte[] rgb565, int width, int height) {
		return this.mOcr.getYData(rgb565, width, height);
	}
	
	public boolean loadImageMem(long pBuffer, int width, int height, int component) {
        boolean result = false;
        if(pBuffer != 0) {
            m_pImage = mOcr.loadImageMem(m_pEngine, pBuffer, width, height, component);
            if(m_pImage != 0) {
                m_ppImage[0x0] = m_pImage;
                result = true;
            }
        }
        return result;
    }
	
	public boolean doImageBCR() {
        boolean result = false;
        int ret = mOcr.doImageBCR(m_pEngine, m_pImage, m_ppField);
        if(ret == 1) {
            m_pField = m_ppField[0];
            m_pCurField = m_pField;
            result = true;
        }
        return result;
    }
	
	public void freeImage() {
        if(mOcr != null) {
            mOcr.freeImage(m_pEngine, m_ppImage);
            m_ppImage[0] = 0;
            m_pImage = 0;
        }
    }
    
    public void freeImgData(long yData) {
        if(mOcr != null) {
            mOcr.FreeRgb(yData);
        }
    }
    
    public void freeBFields() {
        if(mOcr != null) {
            mOcr.freeBField(m_pEngine, m_pField, 0x0);
            m_ppField[0] = 0;
            m_pField = 0;
            m_pCurField = 0;
        }
    }
    
    public boolean isBlurImage(int level) {
        boolean result = false;
        if(mOcr != null) {
            int ret = mOcr.imageChecking(m_pEngine, m_pImage, level);
            if(ret == 0x2) {
                result = true;
            }
        }
        return result;
    }
    
    public boolean isIdCard() {
        boolean result = false;
        if(mOcr != null) {
            int ret = mOcr.GetCardType(m_pEngine, m_pImage);
            if((ret == 0x11) || (ret == 0x14) || (ret == 0x10) || (ret == 0x12)) {
                result = true;
            }
        }
        return result;
    }
    
    public boolean isInRect(Rect rect, Handler mHandler) {
        boolean result = false;
        if(mOcr != null) {
            int[] r = new int[4];
            r[0] = rect.left;
            r[1] = rect.top;
            r[2] = rect.right;
            r[3] = rect.bottom;
            byte ret = mOcr.CheckCardEdgeLine(m_pEngine, m_pImage, r, 0x28, 0x1e, 0x0, 0x0);
            mHandler.sendMessage(mHandler.obtainMessage(0x7, Byte.valueOf(ret)));
            if(ret == 0xf) {
                result = true;
            }
        }
        return result;
    }
    
    public long dupImage(Rect rect) {
        long result = -1;
        if(mOcr != null) {
            int[] r = new int[4];
            r[0x0] = rect.left;
            r[0x1] = rect.top;
            r[0x2] = rect.right;
            r[0x3] = rect.bottom;
            result = mOcr.DupImage(m_pImage, r);
            if(result != 0) {
                freeImage();
                m_pImage = result;
                m_ppImage[0] = m_pImage;
            }
        }
        return result;
    }
    
    public long dupImageOnly(Rect rect) {
        long result = -1;
        if(mOcr != null) {
            int[] r = new int[4];
            
            if(rect != null) {
	            r[0] = rect.left;
	            r[1] = rect.top;
	            r[2] = rect.right;
	            r[3] = rect.bottom;
            }
            result = mOcr.DupImage(m_pImage, r);
        }
        return result;
    }
    
    public int[] getheadImgRect() {
        int[] r = new int[4];
        if(mOcr != null) {
            mOcr.getheadImgRect(m_pField, r);
        }
        return r;
    }
    
    public long getheadImg(long pImage, int[] rect) {
    	long result = -1;
        if (this.mOcr != null)
          result = this.mOcr.DupImage(pImage, rect);
        return result;
    }
    
    public void saveImg(long pImage, String filePath) {
        if(mOcr != null) {
            StringBuffer s = new StringBuffer(filePath);
            s.append('0');
            byte[] buffer = null;
            try {
                buffer = s.toString().getBytes("gbk");
            } catch(UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            buffer[(buffer.length - 1)] = 0;
            mOcr.SaveImage(m_pEngine, pImage, buffer);
        }
    }
    
    public IdCardInfo getIdCardInfo(int keyLanguage) {
        IdCardInfo idCardInfo = new IdCardInfo();
        
        while(!isFieldEnd()) {
	        switch(getFieldId()) {
	            case BIDC_NAME:
	                idCardInfo.setName(getFieldText(keyLanguage).replace("����", ""));
	                break;
	            case BIDC_CARDNO:
	                idCardInfo.setNum(getFieldText(keyLanguage).replace("(wrong number)", ""));
	                break;
	            case BIDC_SEX:
	                idCardInfo.setSex(getFieldText(keyLanguage));
	                break;
	            case BIDC_FOLK:
	                idCardInfo.setFolk(getFieldText(keyLanguage));
	                break;
	            case BIDC_BIRTHDAY:
	                idCardInfo.setBirthday(getFieldText(keyLanguage));
	                break;
	            case BIDC_ADDRESS:
	                idCardInfo.setAddress(getFieldText(keyLanguage).replace("סַ", ""));
	                break;
	            case BIDC_ISSUE_AUTHORITY:
	                idCardInfo.setAuthority(getFieldText(keyLanguage));
	                break;
	            case BIDC_VALID_PERIOD:
	                idCardInfo.setValid(getFieldText(keyLanguage));
	                break;
	            case BIDC_MEMO:
	                idCardInfo.setMemo(" ");
	                break;
	        }
	        getNextField();
        }
        return idCardInfo;
    }
    
    public long yuvToRGB(byte[] yuvBuffer, int witdh, int height, int type) {    
        return mOcr.YuvToRgb(yuvBuffer, witdh, height, type);
    }

}
